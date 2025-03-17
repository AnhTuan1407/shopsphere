package com.tuanha.product.mapper;

import com.tuanha.product.dto.request.SupplierCreationRequest;
import com.tuanha.product.dto.request.SupplierUpdationRequest;
import com.tuanha.product.dto.response.SupplierResponse;
import com.tuanha.product.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    Supplier toSupplier(SupplierCreationRequest request);

    SupplierResponse toSupplierResponse(Supplier supplier);

    void toUpdateSupplier(@MappingTarget Supplier supplier, SupplierUpdationRequest request);
}
