package com.tuanha.sale.repository;

import com.tuanha.sale.entity.VoucherApplicableCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherApplicableCategoryRepository extends JpaRepository<VoucherApplicableCategory, Long> {
}
