package com.daewon.xeno_z1.repository;

import com.daewon.xeno_z1.domain.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsSearchRepository extends JpaRepository<Products, Long> {

    // 카테고리를 기준으로 상품을 검색
    @Query("SELECT p FROM Products p WHERE p.category = :category")
    Page<Products> findByCategory(@Param("category") String category, Pageable pageable);


    // 브랜드명, 이름, 카테고리, 카테고리 sub 검색
    // @Query("SELECT p FROM Products p WHERE p.brandName LIKE %:keyword% OR p.name LIKE %:keyword% OR p.category like %:keyword% OR p.categorySub LIKE %:keyword%")
    // Page<Products> findbrandNameOrNameOrCategoryOrCategorysubBykeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT pc.productColorId FROM ProductsColor pc JOIN pc.products p WHERE p.brandName LIKE %:keyword% OR p.name LIKE %:keyword% OR p.category LIKE %:keyword% OR p.categorySub LIKE %:keyword%")
    Page<Long> findProductColorIdsByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
