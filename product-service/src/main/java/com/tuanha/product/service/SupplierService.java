package com.tuanha.product.service;

import com.tuanha.product.dto.request.SupplierCreationRequest;
import com.tuanha.product.dto.request.SupplierUpdationRequest;
import com.tuanha.product.dto.response.SupplierResponse;
import com.tuanha.product.entity.Supplier;
import com.tuanha.product.exception.AppException;
import com.tuanha.product.exception.ErrorCode;
import com.tuanha.product.mapper.SupplierMapper;
import com.tuanha.product.repository.SupplierRepository;
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

    SupplierMapper supplierMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public SupplierResponse createSupplier(SupplierCreationRequest request) {
        if (!ObjectUtils.isEmpty(supplierRepository.findByName(request.getName())))
            throw new AppException(ErrorCode.SUPPLIER_EXISTS);

        Supplier supplier = supplierMapper.toSupplier(request);
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

    public SupplierResponse getSupplierByName(String name) {
        var supplier = supplierRepository.findByName(name);
        if (ObjectUtils.isEmpty(supplier)) {
            throw new AppException(ErrorCode.SUPPLIER_NOT_EXISTS);
        }

        return supplierMapper.toSupplierResponse(supplier);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_EXISTS));
        supplierRepository.deleteById(id);

        return "Supplier has been deleted.";
    }
}
