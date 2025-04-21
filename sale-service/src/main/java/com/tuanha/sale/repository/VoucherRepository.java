package com.tuanha.sale.repository;

import com.tuanha.sale.entity.Voucher;
import com.tuanha.sale.enums.VoucherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
    List<Voucher> findAllByCreatorId(Long Id);
}
