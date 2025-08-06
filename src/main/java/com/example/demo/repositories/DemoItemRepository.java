package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemoItem;

@Repository
public interface DemoItemRepository extends JpaRepository<DemoItem, Long> {

    @Query("SELECT d FROM DemoItem d WHERE "+
    "(:brandId IS NULL OR d.brand.brandId = :brandId) AND "+
    "(:keyword IS NULL OR d.demoName LIKE %:keyword% OR d.comment LIKE %:keyword%)")
    List<DemoItem> searchByConditions(@Param("brandId") Long brandId, @Param("keyword") String keyword);
}
