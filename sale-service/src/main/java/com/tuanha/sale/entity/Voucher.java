package com.tuanha.sale.entity;

import com.tuanha.sale.enums.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String code;
    String title;
    String description;

    @Enumerated(EnumType.STRING)
    VoucherType voucherType;

    @Nullable
    Integer discountPercent;
    @Nullable
    Float discountAmount;
    int minOrderAmount;
    int maxDiscountAmount;

    @Enumerated(EnumType.STRING)
    CreatorType creatorType;
    @Nullable
    Long creatorId;

    Date startDate;
    Date endDate;

    int totalQuantity;
    @Builder.Default
    int perUserLimit = 1;
    @Builder.Default
    int usedQuantity = 0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    VoucherStatus voucherStatus = VoucherStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    PaymentMethod applicablePayment;

    @OneToMany
    @JoinColumn(name = "voucherId", referencedColumnName = "id")
    List<VoucherApplicableProduct> applicableProducts;

    @OneToMany
    @JoinColumn(name = "voucherId", referencedColumnName = "id")
    List<VoucherApplicableCategory> applicableCategories;
}
