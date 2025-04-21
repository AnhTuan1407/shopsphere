package com.tuanha.sale.controller;

import com.tuanha.sale.dto.request.UserVoucherCreationRequest;
import com.tuanha.sale.dto.request.VoucherCreationRequest;
import com.tuanha.sale.dto.request.VoucherUpdationRequest;
import com.tuanha.sale.dto.response.ApiResponse;
import com.tuanha.sale.dto.response.UserVoucherResponse;
import com.tuanha.sale.dto.response.VoucherResponse;
import com.tuanha.sale.enums.VoucherType;
import com.tuanha.sale.service.VoucherService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/vouchers")
public class VoucherController {
    VoucherService voucherService;

    @PostMapping("")
    ApiResponse<VoucherResponse> createVoucher(@RequestBody VoucherCreationRequest request) {
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.createVoucher(request))
                .build();
    }

    @PostMapping("/claim")
    ApiResponse<UserVoucherResponse> claimVoucher(@RequestBody UserVoucherCreationRequest request) {
        return ApiResponse.<UserVoucherResponse>builder()
                .result(voucherService.claimVoucher(request))
                .build();
    }


    @GetMapping("")
    ApiResponse<List<VoucherResponse>> getAllAdminVouchers() {
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getAllVouchers())
                .build();
    }

    @GetMapping("/supplier")
    ApiResponse<List<VoucherResponse>> getAllSupplierVouchers() {
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getAllVouchersBySupplier())
                .build();
    }

    @GetMapping("/active")
    ApiResponse<List<VoucherResponse>> getActiveVouchers() {
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getActiveVouchers())
                .build();
    }

    @GetMapping("/claimed/{profileId}")
    ApiResponse<List<UserVoucherResponse>> getClaimedVouchersByUser(@PathVariable String profileId) {
        return ApiResponse.<List<UserVoucherResponse>>builder()
                .result(voucherService.getClaimedVouchersByUser(profileId))
                .build();
    }

    @GetMapping("/code/{code}")
    ApiResponse<VoucherResponse> getVoucherByCode(@PathVariable String code) {
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.getVoucherByCode(code))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    ApiResponse<VoucherResponse> updateVoucher(@PathVariable Long id, @RequestBody VoucherUpdationRequest request) {
        return ApiResponse.<VoucherResponse>builder()
                .result(voucherService.updateVoucher(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER')")
    ApiResponse<Void> deleteVoucher(@PathVariable Long id) {
        voucherService.deleteVoucher(id);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/supplier/{id}")
    ApiResponse<List<VoucherResponse>> getAllVouchersBySupplierId(@PathVariable("id") Long id) {
        return ApiResponse.<List<VoucherResponse>>builder()
                .result(voucherService.getAllVouchersBySupplierId(id))
                .build();
    }

    @GetMapping("/types")
    ApiResponse<List<VoucherType>> getAllVoucherTypes() {
        return ApiResponse.<List<VoucherType>>builder()
                .result(List.of(VoucherType.values()))
                .build();
    }
}
