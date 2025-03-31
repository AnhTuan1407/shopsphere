package com.tuanha.order.repository;

import com.tuanha.order.entity.Order;
import com.tuanha.order.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.profileId = :profileId")
    List<Order> findAllByProfileId(@Param("profileId") String profileId);

    @Query("SELECT o FROM OrderInfo o WHERE o.profileId = :profileId")
    List<OrderInfo> findAllOrderInfoByProfileId(@Param("profileId") String profileId);
}
