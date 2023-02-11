package com.example.final_workshop.repository;

import com.example.final_workshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    Optional<Product> findByEanCode(String eanCode);
}
