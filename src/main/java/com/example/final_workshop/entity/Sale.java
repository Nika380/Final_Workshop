package com.example.final_workshop.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "sales", schema = "public", catalog = "postgres")
@SequenceGenerator(name = "sales_id", sequenceName = "sales_id_seq", allocationSize = 1)
public class Sale {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sales_id")
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "product_id")
    private String productId;
    @Column(name = "quantity")
    private Integer quantity;
    @Basic
    @Column(name = "sell_price")
    private Integer sellPrice;
    @Basic
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "sell_date")
    private LocalDate sellDate;
    @Column(name = "selled_by")
    private String selledBy;

    @PrePersist
    void prePersist() {
        sellDate = LocalDate.now();
    }

}
