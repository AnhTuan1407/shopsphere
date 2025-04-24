package com.tuanha.product.service;

import com.tuanha.product.dto.request.SupplierCreationRequest;
import com.tuanha.product.dto.request.SupplierUpdationRequest;
import com.tuanha.product.dto.request.UserCreationRequest;
import com.tuanha.product.dto.response.ProductResponse;
import com.tuanha.product.dto.response.SupplierResponse;
import com.tuanha.product.entity.Product;
import com.tuanha.product.entity.Supplier;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.SupplierMapper;
import com.tuanha.product.repository.SupplierRepository;
import com.tuanha.product.repository.httpClient.UserClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierService {
    SupplierRepository supplierRepository;
    UserClient userClient;
    SupplierMapper supplierMapper;

    public SupplierResponse createSupplier(SupplierCreationRequest request) {
        if (!ObjectUtils.isEmpty(supplierRepository.findByName(request.getName())))
            throw new AppException(ErrorCode.SUPPLIER_EXISTS);

        Supplier supplier = supplierMapper.toSupplier(request);

        var userCreationRequest = new UserCreationRequest(
                request.getPhoneNumber(),
                request.getPassword(),
                "",
                request.getName(),
                request.getContactEmail(),
                request.getPhoneNumber(),
                true
        );
        var apiResponse = userClient.createUser(userCreationRequest);

        supplier.setUserId(apiResponse.getResult().getId());
        return supplierMapper.toSupplierResponse(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> getAllSuppliers() {
        return supplierRepository.findAll().stream().map(supplierMapper::toSupplierResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public SupplierResponse updateSupplier(SupplierUpdationRequest request, Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_EXISTS));
        supplierMapper.toUpdateSupplier(supplier, request);

        return supplierMapper.toSupplierResponse(supplierRepository.save(supplier));
    }

    public SupplierResponse getSupplierById(Long id) {
        var supplier = supplierRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_EXISTS));

        return supplierMapper.toSupplierResponse(supplier);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_EXISTS));
        supplierRepository.deleteById(id);

        return "Supplier has been deleted.";
    }

    public SupplierResponse getSupplierByUserId(String id) {
        var supplier = supplierRepository.findByUserId(id);
        return supplierMapper.toSupplierResponse(supplier);
    }

    public List<SupplierResponse> getAllSupplierContainingName(String name) {
        String[] keywords = name.trim().toLowerCase().split("\\s+");

        List<Supplier> allSuppliers = supplierRepository.findAll();

        List<SupplierResponse> supplierResponses = allSuppliers.stream()
                .filter(supplier -> {
                    String supplierNameLower = supplier.getName().toLowerCase();
                    for (String keyword : keywords) {
                        if (supplierNameLower.contains(keyword)) {
                            return true;
                        }
                    }
                    return false;
                })
                .map(supplierMapper::toSupplierResponse)
                .toList();

        return supplierResponses;
    }
}
