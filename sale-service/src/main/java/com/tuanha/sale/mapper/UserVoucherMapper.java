package com.tuanha.sale.mapper;

import com.tuanha.sale.dto.request.UserVoucherCreationRequest;
import com.tuanha.sale.dto.response.UserVoucherResponse;
import com.tuanha.sale.entity.UserVoucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserVoucherMapper {
    UserVoucher toUserVoucher(UserVoucherCreationRequest request);

    @Mapping(source = "voucher", target = "voucher")
    UserVoucherResponse toUserVoucherResponse(UserVoucher userVoucher);
}
