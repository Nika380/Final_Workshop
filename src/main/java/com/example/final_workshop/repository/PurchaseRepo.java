package com.example.final_workshop.repository;

import com.example.final_workshop.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PurchaseRepo extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {
}
