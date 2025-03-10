package com.tuanha.product.controller;

import com.tuanha.product.dto.request.SupplierCreationRequest;
import com.tuanha.product.dto.request.SupplierUpdationRequest;
import com.tuanha.product.dto.response.ApiResponse;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.dto.response.SupplierResponse;
import com.tuanha.product.service.SupplierService;
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
@RequestMapping("/suppliers")
public class SupplierController {
    SupplierService supplierService;

    @GetMapping("")
    ApiResponse<List<SupplierResponse>> getAllSuppliers() {
        return ApiResponse.<List<SupplierResponse>>builder()
                .result(supplierService.getAllSuppliers())
                .build();
    }

    @PostMapping
    ApiResponse<SupplierResponse> createSupplier(@RequestBody SupplierCreationRequest request) {
        return ApiResponse.<SupplierResponse>builder()
                .result(supplierService.createSupplier(request))
                .build();
    }

    @PostMapping("/{id}")
    ApiResponse<SupplierResponse> updateSupplier(@RequestBody SupplierUpdationRequest request,
                                                 @PathVariable("id") Long id) {
        return ApiResponse.<SupplierResponse>builder()
                .result(supplierService.updateSupplier(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteSupplier(@PathVariable("id") Long id) {
        return ApiResponse.<String>builder()
                .result(supplierService.deleteSupplier(id))
                .build();
    }

//    @GetMapping("/{name}")
//    ApiResponse<SupplierResponse> getSupplierByName(@PathVariable("name") String name) {
//        return ApiResponse.<SupplierResponse>builder()
//                .result(supplierService.getSupplierByName(name))
//                .build();
//    }
}
