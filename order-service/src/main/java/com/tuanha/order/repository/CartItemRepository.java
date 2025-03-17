package com.tuanha.order.repository;

import com.tuanha.order.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    boolean existsByProductVariantId(Long productVariantId);
    CartItem findByProductVariantId(Long productVariantId);
}
