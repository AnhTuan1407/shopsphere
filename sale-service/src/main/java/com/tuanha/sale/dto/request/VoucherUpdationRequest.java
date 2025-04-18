package com.tuanha.sale.dto.request;

import com.tuanha.sale.enums.CreatorType;
import com.tuanha.sale.enums.PaymentMethod;
import com.tuanha.sale.enums.VoucherType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoucherUpdationRequest {
    String code;
    String title;
    String description;
    VoucherType voucherType;
    Integer discountPercent;
    Float discountAmount;
    int minOrderAmount;
    int maxDiscountAmount;
    CreatorType creatorType;
    Long creatorId;
    Date startDate;
    Date endDate;
    int totalQuantity;
    PaymentMethod applicablePayment;
    List<VoucherApplicableProductRequest> applicableProducts;
    List<VoucherApplicableCategoryRequest> applicableCategories;
}
