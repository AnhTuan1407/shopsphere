package com.tuanha.product.controller;

import com.tuanha.product.dto.request.CategoryCreationRequest;
import com.tuanha.product.dto.response.ApiResponse;
import com.tuanha.product.dto.response.CategoryResponse;
import com.tuanha.product.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryCreationRequest request) {
        var category = categoryService.createCategory(request);
        return ApiResponse.<CategoryResponse>builder()
                .result(category)
                .build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategories() {
        return  ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .build();
    }
}
