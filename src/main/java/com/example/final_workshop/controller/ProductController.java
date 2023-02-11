package com.example.final_workshop.controller;

import com.example.final_workshop.entity.Product;
import com.example.final_workshop.search.SearchParams;
import com.example.final_workshop.security.SecUser;
import com.example.final_workshop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<Product> getProductsList(SearchParams params){
        return productService.getProductsList(params);
    }

    @PostMapping
    @Transactional
    public Product addProduct(@RequestBody Product product, @AuthenticationPrincipal SecUser user) {
        return productService.addProduct(product, user).getBody();
    }
    @GetMapping("/{id}")
    public Product getById(@PathVariable String id) {
        return productService.findWithCode(id);
    }
}
