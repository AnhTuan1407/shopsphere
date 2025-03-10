package com.tuanha.product.mapper;

import com.tuanha.product.dto.request.CategoryCreationRequest;
import com.tuanha.product.dto.request.CategoryUpdationRequest;
import com.tuanha.product.dto.response.CategoryResponse;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.entity.Category;
import com.tuanha.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);

    @Mapping(target = "products", source = "products")
    CategoryResponse toCategoryResponse(Category category);

    void toUpdateCategory(@MappingTarget Category category, CategoryUpdationRequest request);
}
