package com.example.final_workshop.search;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SearchParams {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTo;
    private Integer pageSize = 5;
    private Integer page = 0;
    private Integer makerId;
    private String productName;
    private Integer categoryId;
    private Integer sellPrice;
}
