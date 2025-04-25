package com.tuanha.sale.mapper;

import com.tuanha.sale.dto.request.FlashSaleCreationRequest;
import com.tuanha.sale.dto.request.FlashSaleItemCreationRequest;
import com.tuanha.sale.dto.request.FlashSaleItemUpdationRequest;
import com.tuanha.sale.dto.response.FlashSaleItemResponse;
import com.tuanha.sale.dto.response.FlashSaleResponse;
import com.tuanha.sale.entity.FlashSale;
import com.tuanha.sale.entity.FlashSaleItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlashSaleItemMapper {
    FlashSaleItemResponse toFlashSaleItemResponse(FlashSaleItem flashSaleItem);

    FlashSaleItem toFlashSaleItem(FlashSaleItemCreationRequest request);

    void toUpdateFlashSaleItem(@MappingTarget FlashSaleItem flashSaleItem, FlashSaleItemUpdationRequest request);
}
