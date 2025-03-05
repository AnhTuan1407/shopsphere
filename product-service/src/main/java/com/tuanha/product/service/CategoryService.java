package com.tuanha.product.service;

import com.tuanha.product.dto.request.CategoryCreationRequest;
import com.tuanha.product.dto.response.CategoryResponse;
import com.tuanha.product.entity.Category;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.CategoryMapper;
import com.tuanha.product.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(CategoryCreationRequest request) {
        if (categoryRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXISTS);
        }
        Category category = categoryMapper.toCategory(request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getAllCategories() {
        List<CategoryResponse> categoryResponseList = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();

        if (CollectionUtils.isEmpty(categoryResponseList))
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);

        return categoryResponseList;
    }
}
