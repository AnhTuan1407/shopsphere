package com.tuanha.sale.dto.request;

import com.tuanha.sale.entity.VoucherApplicableCategory;
import com.tuanha.sale.entity.VoucherApplicableProduct;
import com.tuanha.sale.enums.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoucherCreationRequest {
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
