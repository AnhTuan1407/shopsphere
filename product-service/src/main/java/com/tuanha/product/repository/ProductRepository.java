package com.tuanha.product.repository;

import com.tuanha.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findBySupplierId(Long id);

    List<Product> findByCategoryId(Long id);

    @Query("SELECT p FROM Product p JOIN p.productVariants pv WHERE pv.id = :variantId")
    Product findByProductVariantId(@Param("variantId") Long variantId);

    @Query("SELECT p FROM Product p WHERE " +
            "LOWER(p.name) LIKE %:kw1% AND LOWER(p.name) LIKE %:kw2%")
    List<Product> findByKeywords(@Param("kw1") String kw1, @Param("kw2") String kw2);

    Integer countProductBySupplierId(Long id);
}
