package com.tuanha.sale.service;

import com.tuanha.sale.dto.request.*;
import com.tuanha.sale.dto.response.UserVoucherResponse;
import com.tuanha.sale.dto.response.VoucherResponse;
import com.tuanha.sale.entity.UserVoucher;
import com.tuanha.sale.entity.VoucherApplicableCategory;
import com.tuanha.sale.entity.VoucherApplicableProduct;
import com.tuanha.sale.enums.CreatorType;
import com.tuanha.sale.exception.AppException;
import com.tuanha.sale.exception.ErrorCode;
import com.tuanha.sale.mapper.UserVoucherMapper;
import com.tuanha.sale.mapper.VoucherMapper;
import com.tuanha.sale.repository.UserVoucherRepository;
import com.tuanha.sale.repository.VoucherApplicableCategoryRepository;
import com.tuanha.sale.repository.VoucherApplicableProductRepository;
import com.tuanha.sale.repository.VoucherRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class VoucherService {
    VoucherRepository voucherRepository;
    UserVoucherRepository userVoucherRepository;
    VoucherMapper voucherMapper;
    UserVoucherMapper userVoucherMapper;
    VoucherApplicableCategoryRepository voucherApplicableCategoryRepository;
    VoucherApplicableProductRepository voucherApplicableProductRepository;

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public VoucherResponse createVoucher(VoucherCreationRequest request) {
        var voucher = voucherMapper.toVoucher(request);
        var voucherResponse = voucherMapper.toVoucherResponse(voucherRepository.save(voucher));
        if (request.getApplicableProducts() != null && !request.getApplicableProducts().isEmpty()) {
            for (VoucherApplicableProductRequest v : request.getApplicableProducts()) {
                var voucherApplicable = new VoucherApplicableProduct(
                        null,
                        v.getProductId(),
                        voucherResponse.getId()
                );
                voucherApplicableProductRepository.save(voucherApplicable);
            }
        }
        if (request.getApplicableCategories() != null && !request.getApplicableCategories().isEmpty()) {
            for (VoucherApplicableCategoryRequest v : request.getApplicableCategories()) {
                var voucherApplicable = new VoucherApplicableCategory(
                        null,
                        v.getCategoryId(),
                        voucherResponse.getId()
                );
                voucherApplicableCategoryRepository.save(voucherApplicable);
            }
        }
        return voucherResponse;
    }

    @Transactional
    public List<UserVoucherResponse> claimVoucher(UserVoucherCreationRequest request) {
        var voucher = voucherRepository.findById(request.getVoucherId())
                .orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_FOUND));

        // Kiểm tra số lượng đã claim
        int claimedCount = userVoucherRepository.countByProfileIdAndVoucherId(
                request.getProfileId(), request.getVoucherId());
        // Nếu vượt quá perUserLimit thì từ chối
        if (claimedCount >= voucher.getPerUserLimit()) {
            throw new AppException(ErrorCode.VOUCHER_CLAIM_LIMIT_REACHED);
        }
        int claimable = voucher.getPerUserLimit() - claimedCount;
        if (voucher.getTotalQuantity() < claimable) {
            throw new AppException(ErrorCode.VOUCHER_OUT_OF_STOCK);
        }
        List<UserVoucher> newUserVouchers = new ArrayList<>();
        for (int i = 0; i < claimable; i++) {
            newUserVouchers.add(UserVoucher.builder()
                    .profileId(request.getProfileId())
                    .supplierId(request.getSupplierId())
                    .voucher(voucher)
                    .claimedAt(LocalDate.now())
                    .used(false)
                    .build());
        }
        List<UserVoucher> savedVouchers = userVoucherRepository.saveAll(newUserVouchers);

        voucher.setTotalQuantity(voucher.getTotalQuantity() - claimable);
        voucherRepository.save(voucher);

        return savedVouchers.stream()
                .map(userVoucherMapper::toUserVoucherResponse).toList();
    }

    public List<VoucherResponse> getAllVouchers() {
        return voucherRepository.findAll()
                .stream()
                .map(voucherMapper::toVoucherResponse)
                .filter(v -> v.getCreatorType() == CreatorType.ADMIN)
                .toList();
    }

    public List<VoucherResponse> getAllVouchersBySupplier() {
        return voucherRepository.findAll()
                .stream()
                .map(voucherMapper::toVoucherResponse)
                .filter(v -> v.getCreatorType() == CreatorType.SUPPLIER)
                .toList();
    }

    public VoucherResponse getVoucherByCode(String code) {
        var voucher = voucherRepository.findByCode(code);
        if (ObjectUtils.isEmpty(voucher)) {
            throw new AppException(ErrorCode.VOUCHER_NOT_FOUND);
        }
        return voucherMapper.toVoucherResponse(voucher);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public VoucherResponse updateVoucher(Long id, VoucherUpdationRequest request) {
        var voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_FOUND));

        voucherMapper.toVoucherUpdate(voucher, request);
        return voucherMapper.toVoucherResponse(voucherRepository.save(voucher));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    public void deleteVoucher(Long id) {
        var voucher = voucherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_FOUND));
        voucherRepository.delete(voucher);
    }

    public List<UserVoucherResponse> getClaimedVouchersByUser(String profileId) {
        return userVoucherRepository.findByProfileId(profileId)
                .stream()
                .map(userVoucherMapper::toUserVoucherResponse)
                .toList();
    }

    public List<VoucherResponse> getActiveVouchers() {
        var now = new Date();
        return voucherRepository.findAll()
                .stream()
                .filter(v -> v.getStartDate().before(now) && v.getEndDate().after(now))
                .map(voucherMapper::toVoucherResponse)
                .toList();
    }

    public List<VoucherResponse> getAllVouchersBySupplierId(Long id) {
        return voucherRepository.findAllByCreatorId(id)
                .stream()
                .map(voucherMapper::toVoucherResponse)
                .filter(v -> v.getCreatorType() == CreatorType.SUPPLIER)
                .toList();
    }

    public UserVoucherResponse useVoucher(Long id) {
        UserVoucher userVoucher = userVoucherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VOUCHER_NOT_FOUND));
        userVoucher.setUsed(true);
        return userVoucherMapper.toUserVoucherResponse(userVoucherRepository.save(userVoucher));
    }
}
