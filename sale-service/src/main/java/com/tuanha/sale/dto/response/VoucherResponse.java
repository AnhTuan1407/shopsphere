package com.tuanha.sale.dto.response;

import com.tuanha.sale.dto.request.VoucherApplicableCategoryRequest;
import com.tuanha.sale.dto.request.VoucherApplicableProductRequest;
import com.tuanha.sale.enums.CreatorType;
import com.tuanha.sale.enums.PaymentMethod;
import com.tuanha.sale.enums.VoucherType;
import jakarta.annotation.Nullable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoucherResponse {
    Long id;
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
    List<VoucherApplicableProductResponse> applicableProducts;
    List<VoucherApplicableCategoryResponse> applicableCategories;
}
