package com.tuanha.sale.repository;

import com.tuanha.sale.entity.VoucherApplicableProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherApplicableProductRepository extends JpaRepository<VoucherApplicableProduct, Long> {
}
