package com.example.final_workshop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Entity
@Data
@Table(name = "products", schema = "public", catalog = "postgres")
public class Product {
    @Id
    @Column(name = "ean_code")
    private String eanCode;
    @Basic
    @Column(name = "product_name")
    private String productName;
    @Basic
    @Column(name = "description")
    private String description;
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic
    @Column(name = "maker_id")
    private Integer makerId;
    @Basic
    @Column(name = "sell_price")
    private Integer sellPrice;
    @Basic
    @Column(name = "remaining")
    private Integer remaining;
    @Column(name = "added_by")
    private String addedBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "added_at")
    private LocalDate addetAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @PrePersist
    void pre() {
        addetAt = LocalDate.now();
        updatedAt = LocalDateTime.now();
    }

    public static class EanGenerator {
        private static final int EAN_LENGTH = 13;
        private static final String CHARACTERS = "0123456789";

        public static String generateEan() {
            StringBuilder ean = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < EAN_LENGTH; i++) {
                ean.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }

            return ean.toString();
        }

    }

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", insertable = false,updatable = false)
    private Category category;
    @OneToOne
    @JoinColumn(name = "maker_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Maker maker;

}
