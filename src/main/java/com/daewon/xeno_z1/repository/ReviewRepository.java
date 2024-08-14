package com.daewon.xeno_z1.repository;


import com.daewon.xeno_z1.domain.Orders;
import com.daewon.xeno_z1.domain.Review;
import com.daewon.xeno_z1.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.order.productsColorSize.productsColor.productColorId = :productColorId ")
    Page<Review> findByProductColorId(@Param("productColorId") Long productColorId, Pageable pageable);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.order.productsColorSize.productsColor.productColorId = :productColorId")
    long countByProductColorId(Long productColorId); // 리뷰 작성한 수

    // 리뷰 작성자의 userId를 반환하도록 하는 메서드
    @Query("select r.users.userId from Review r where r.reviewId = :reviewId")
    Optional<Long> findAuthorUserIdById(Long reviewId);


    @Query("select r from Review r where r.users = :users and r.order = :orders")
    Review findByUsersAndOrders(Users users, Orders orders);

}