package com.tuanha.order.repository;

import com.tuanha.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByProfileId(String profileId);

    @Query("SELECT c FROM Cart c JOIN c.cartItems ci WHERE ci.id = :cartItemId")
    Cart findByCartItemId(@Param("cartItemId") Long cartItemId);

}
