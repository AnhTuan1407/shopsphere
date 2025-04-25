package com.tuanha.sale.mapper;

import com.tuanha.sale.dto.request.FlashSaleCreationRequest;
import com.tuanha.sale.dto.request.FlashSaleItemUpdationRequest;
import com.tuanha.sale.dto.request.FlashSaleUpdationRequest;
import com.tuanha.sale.dto.response.FlashSaleResponse;
import com.tuanha.sale.entity.FlashSale;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlashSaleMapper {
    FlashSaleResponse toFlashSaleResponse(FlashSale flashSale);

    FlashSale toFlashSale(FlashSaleCreationRequest request);

    void toUpdateFlashSale(@MappingTarget FlashSale flashSale, FlashSaleUpdationRequest request);
}
