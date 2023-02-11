package com.example.final_workshop.repository;

import com.example.final_workshop.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepo extends JpaRepository<Sale,Integer>, JpaSpecificationExecutor<Sale> {
}
