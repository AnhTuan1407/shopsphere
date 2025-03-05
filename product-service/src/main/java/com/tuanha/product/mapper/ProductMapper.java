package com.tuanha.product.mapper;

import com.tuanha.product.dto.request.ProductCreationRequest;
import com.tuanha.product.dto.request.ProductUpdationRequest;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProduct(ProductCreationRequest request);
    ProductResponse toProductResponse(Product product);
    void toUpdateProduct(@MappingTarget Product product, ProductUpdationRequest request);
}
