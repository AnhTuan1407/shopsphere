package com.tuanha.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String profileId;
    @Builder.Default
    double totalPrice = 0;

    @OneToMany(mappedBy = "cart")
    @ToString.Exclude
    @JsonIgnore
    List<CartItem> cartItems;
}
