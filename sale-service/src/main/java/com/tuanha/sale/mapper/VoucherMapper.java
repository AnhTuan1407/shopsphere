package com.tuanha.sale.mapper;

import com.tuanha.sale.dto.request.VoucherCreationRequest;
import com.tuanha.sale.dto.request.VoucherUpdationRequest;
import com.tuanha.sale.dto.response.VoucherResponse;
import com.tuanha.sale.entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VoucherMapper {
    Voucher toVoucher(VoucherCreationRequest request);

    VoucherResponse toVoucherResponse(Voucher voucher);

    void toVoucherUpdate(@MappingTarget Voucher voucher, VoucherUpdationRequest request);
}
