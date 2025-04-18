package com.tuanha.sale.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserVoucherResponse {
    Long id;
    VoucherResponse voucher;
    String profileId;
    SupplierResponse supplier;
    boolean isUse;
    Date claimedAt;
}
