package com.example.final_workshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "purchases", schema = "public", catalog = "postgres")
public class Purchase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "product_id")
    private String productId;
    @Basic
    @Column(name = "purchase_price")
    private Integer purchasePrice;
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;
    @Column(name = "purchased_by")
    private String purchasedBy;

    @PrePersist
    void pre() {
        purchaseDate = LocalDateTime.now();
    }

  }
