package com.tuanha.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String profileId;
    String fullName;
    String phoneNumber;
    String city;
    String district;
    String ward;
    String detailAddress;
    boolean defaultAddress;
}
