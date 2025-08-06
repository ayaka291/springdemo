package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemoItem;

@Repository
public interface DemoItemRepository extends JpaRepository<DemoItem, Long> {
    
}
