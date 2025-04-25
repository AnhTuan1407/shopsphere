package com.tuanha.sale.service;

import com.tuanha.sale.dto.request.FlashSaleCreationRequest;
import com.tuanha.sale.dto.request.FlashSaleItemCreationRequest;
import com.tuanha.sale.dto.response.FlashSaleResponse;
import com.tuanha.sale.dto.response.ProductVariantResponse;
import com.tuanha.sale.entity.FlashSale;
import com.tuanha.sale.entity.FlashSaleItem;
import com.tuanha.sale.enums.DiscountType;
import com.tuanha.sale.enums.FlashSaleItemStatus;
import com.tuanha.sale.enums.FlashSaleStatus;
import com.tuanha.sale.exception.AppException;
import com.tuanha.sale.exception.ErrorCode;
import com.tuanha.sale.mapper.FlashSaleItemMapper;
import com.tuanha.sale.mapper.FlashSaleMapper;
import com.tuanha.sale.repository.FlashSaleItemRepository;
import com.tuanha.sale.repository.FlashSaleRepository;
import com.tuanha.sale.repository.httpClient.ProductClient;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class FlashSaleService {
    FlashSaleRepository flashSaleRepository;
    FlashSaleItemRepository flashSaleItemRepository;
    FlashSaleMapper flashSaleMapper;
    FlashSaleItemMapper flashSaleItemMapper;
    ProductClient productClient;

    @Transactional
    public FlashSaleResponse createFlashSale(FlashSaleCreationRequest request) {
        validateFlashSaleTime(request.getStartTime(), request.getEndTime());

        var flashSale = flashSaleMapper.toFlashSale(request);
        LocalDateTime now = LocalDateTime.now();
        if (request.getStartTime().isAfter(now)) {
            flashSale.setStatus(FlashSaleStatus.UPCOMING);
        } else if (!request.getEndTime().isBefore(now)) {
            flashSale.setStatus(FlashSaleStatus.ACTIVE);
        } else {
            flashSale.setStatus(FlashSaleStatus.ENDED);
        }
        flashSale = flashSaleRepository.save(flashSale);

        for (FlashSaleItemCreationRequest itemRequest : request.getFlashSaleItems()) {
            var product = productClient.getProductById(itemRequest.getProductId());
            var variants = product.getResult().getVariants();
            if (variants == null || variants.isEmpty()) {
                throw new AppException(ErrorCode.PRODUCT_VARIANT_NOT_FOUND);
            }
            for (ProductVariantResponse variant : variants) {
                if (variant.getId() == null) continue;
                BigDecimal flashPrice = calculateDiscountedPrice(
                        variant.getPrice(), itemRequest.getDiscountType(), itemRequest.getDiscountValue()
                );

                validateFlashSaleItem(itemRequest, variant.getPrice(), variant.getAvailableQuantity(), flashPrice);

                FlashSaleItem flashSaleItem = FlashSaleItem.builder()
                        .productId(itemRequest.getProductId())
                        .productVariantId(variant.getId())
                        .originalPrice(BigDecimal.valueOf(variant.getPrice()))
                        .flashSalePrice(flashPrice)
                        .discountType(itemRequest.getDiscountType())
                        .discountValue(itemRequest.getDiscountValue())
                        .totalQuantity(itemRequest.getTotalQuantity())
                        .maxPerUser(itemRequest.getMaxPerUser())
                        .flashSale(flashSale)
                        .status(FlashSaleItemStatus.ACTIVE)
                        .productName(product.getResult().getName())
                        .imageUrl(product.getResult().getImageUrl())
                        .variantType(variant.getColor().isEmpty() ? variant.getSize() : variant.getColor())
                        .build();

                flashSaleItemRepository.save(flashSaleItem);
            }
        }

        return flashSaleMapper.toFlashSaleResponse(flashSale);
    }

    private void validateFlashSaleTime(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end)) {
            throw new AppException(ErrorCode.FLASH_SALE_INVALID_TIME);
        }
    }

    private BigDecimal calculateDiscountedPrice(double price, DiscountType type, BigDecimal discount) {
        BigDecimal original = BigDecimal.valueOf(price);
        BigDecimal result = switch (type) {
            case PERCENTAGE ->
                    original.subtract(original.multiply(discount).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP));
            case AMOUNT -> original.subtract(discount);
        };
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private void validateFlashSaleItem(
            FlashSaleItemCreationRequest item,
            double originalPriceDouble,
            int availableQuantity,
            BigDecimal calculatedPrice
    ) {
        BigDecimal originalPrice = BigDecimal.valueOf(originalPriceDouble).setScale(2, RoundingMode.HALF_UP);

        if (item.getTotalQuantity() <= 0 || item.getMaxPerUser() <= 0) {
            throw new AppException(ErrorCode.FLASH_SALE_ITEM_INVALID_QUANTITY);
        }

        if (item.getMaxPerUser() > item.getTotalQuantity()) {
            throw new AppException(ErrorCode.FLASH_SALE_ITEM_LIMIT_EXCEED);
        }

        if (item.getTotalQuantity() > availableQuantity) {
            throw new AppException(ErrorCode.FLASH_SALE_ITEM_QUANTITY_EXCEEDS_AVAILABLE);
        }

        if (item.getDiscountType() == DiscountType.PERCENTAGE) {
            if (item.getDiscountValue().compareTo(BigDecimal.ZERO) < 0 ||
                    item.getDiscountValue().compareTo(BigDecimal.valueOf(100)) > 0) {
                throw new AppException(ErrorCode.FLASH_SALE_INVALID_DISCOUNT_PERCENTAGE);
            }
        } else if (item.getDiscountType() == DiscountType.AMOUNT) {
            if (item.getDiscountValue().compareTo(BigDecimal.ZERO) < 0 ||
                    item.getDiscountValue().compareTo(originalPrice) > 0) {
                throw new AppException(ErrorCode.FLASH_SALE_INVALID_DISCOUNT_AMOUNT);
            }
        } else {
            throw new AppException(ErrorCode.FLASH_SALE_INVALID_DISCOUNT_TYPE);
        }

        if (calculatedPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AppException(ErrorCode.FLASH_SALE_INVALID_RESULT_PRICE);
        }
    }

    public List<FlashSaleResponse> getAllFlashSales() {
        return flashSaleRepository.findAll().stream()
                .map(flashSaleMapper::toFlashSaleResponse)
                .toList();
    }

    public FlashSaleResponse getFlashSaleById(Long id) {
        var flashSale = flashSaleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.FLASH_SALE_NOT_FOUND));
        return flashSaleMapper.toFlashSaleResponse(flashSale);
    }

    public List<FlashSaleResponse> getActiveFlashSales() {
        LocalDateTime now = LocalDateTime.now();
        return flashSaleRepository.findByStartTimeBeforeAndEndTimeAfter(now, now).stream()
                .map(flashSaleMapper::toFlashSaleResponse)
                .toList();
    }

    public List<FlashSaleResponse> getUpcomingFlashSales() {
        LocalDateTime now = LocalDateTime.now();
        return flashSaleRepository.findByStartTimeAfter(now).stream()
                .map(flashSaleMapper::toFlashSaleResponse)
                .toList();
    }

    public List<FlashSaleResponse> getFlashSaleBySupplierId(Long id) {
        return flashSaleRepository.findAllBySupplierId(id).stream()
                .map(flashSaleMapper::toFlashSaleResponse)
                .toList();
    }
}

