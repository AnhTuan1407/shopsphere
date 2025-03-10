package com.tuanha.product.mapper;

import com.tuanha.product.dto.request.ProductVariantCreationRequest;
import com.tuanha.product.dto.request.ProductVariantUpdationRequest;
import com.tuanha.product.dto.response.ProductVariantResponse;
import com.tuanha.product.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductVariantMapper {
    ProductVariant toProductVariant(ProductVariantCreationRequest request);

    ProductVariantResponse toProductVariantResponse(ProductVariant productVariant);

    void toUpdateProductVariant(@MappingTarget ProductVariant productVariant, ProductVariantUpdationRequest request);
}
