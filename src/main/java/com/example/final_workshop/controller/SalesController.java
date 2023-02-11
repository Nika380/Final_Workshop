package com.example.final_workshop.controller;

import com.example.final_workshop.entity.Sale;
import com.example.final_workshop.repository.ProductRepo;
import com.example.final_workshop.search.SearchParams;
import com.example.final_workshop.security.SecUser;
import com.example.final_workshop.service.sale.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGER') || hasAuthority('CASHIER')" )
public class SalesController {
    private final SaleService service;

    @GetMapping("/sales")
    public Page<Sale> getListOfSales(SearchParams params) {
        return service.getSalesList(params);
    }

    @PostMapping("/products/{code}/sales")
    @Transactional
    public ResponseEntity<Sale> makeSale(@PathVariable String code, @RequestBody Sale sale, @AuthenticationPrincipal SecUser user) {
        return service.makeSale(code, sale, user);
    }
}
