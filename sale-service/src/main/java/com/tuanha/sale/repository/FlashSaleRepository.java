package com.tuanha.sale.repository;

import com.tuanha.sale.entity.FlashSale;
import com.tuanha.sale.enums.FlashSaleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlashSaleRepository extends JpaRepository<FlashSale, Long> {
    List<FlashSale> findByStartTimeAfter(LocalDateTime now);
    List<FlashSale> findByStartTimeBeforeAndEndTimeAfter(LocalDateTime start, LocalDateTime end);
    List<FlashSale> findAllBySupplierId(Long id);
}
