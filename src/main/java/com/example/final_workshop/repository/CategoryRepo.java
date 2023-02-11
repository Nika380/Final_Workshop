package com.example.final_workshop.repository;

import com.example.final_workshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
