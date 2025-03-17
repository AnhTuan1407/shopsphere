package com.tuanha.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tuanha.order.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "profile_id")
    String profileId;
    LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    StatusOrder statusOrder;

    Double totalPrice;

    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    List<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(name = "order_info_id")
    OrderInfo orderInfo;
}
