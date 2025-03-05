package com.tuanha.product.mapper;

import com.tuanha.product.dto.request.CategoryCreationRequest;
import com.tuanha.product.dto.request.CategoryUpdationRequest;
import com.tuanha.product.dto.response.CategoryResponse;
import com.tuanha.product.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryCreationRequest request);
    CategoryResponse toCategoryResponse(Category category);
    void toUpdateCategory (@MappingTarget Category category, CategoryUpdationRequest request);
}
