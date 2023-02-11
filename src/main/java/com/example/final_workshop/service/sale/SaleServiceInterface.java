package com.example.final_workshop.service.sale;

import com.example.final_workshop.entity.Sale;
import com.example.final_workshop.search.SearchParams;
import com.example.final_workshop.security.SecUser;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SaleServiceInterface {
    ResponseEntity<Sale> makeSale(String eanCode, Sale sale, SecUser user);
    Page<Sale> getSalesList(SearchParams params);
}
