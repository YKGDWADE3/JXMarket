package com.example.JXMarket.repository;

import com.example.JXMarket.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

    Inventory findByProductId(Long productId);
}
