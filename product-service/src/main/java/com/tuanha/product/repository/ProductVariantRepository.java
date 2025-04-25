package com.tuanha.product.repository;

import com.tuanha.product.dto.response.ProductVariantResponse;
import com.tuanha.product.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findAllByProductId(Long id);
}
