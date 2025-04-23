package com.tuanha.sale.repository;

import com.tuanha.sale.entity.UserVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVoucherRepository extends JpaRepository<UserVoucher, Long> {
    List<UserVoucher> findByProfileId(String profileId);

    boolean existsByProfileIdAndVoucherId(String profileId, Long voucherId);

    int countByProfileIdAndVoucherId(String profileId, Long voucherId);

}
