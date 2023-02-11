package com.example.final_workshop.service.product;

import com.example.final_workshop.entity.Product;
import com.example.final_workshop.entity.Sale;
import com.example.final_workshop.search.SearchParams;
import com.example.final_workshop.security.SecUser;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductServiceInterface {
    ResponseEntity<Product> addProduct(Product product, SecUser user);
    Page<Product> getProductsList(SearchParams params);
    Product findWithCode(String code);
}
