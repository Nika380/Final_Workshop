package com.example.final_workshop.service.product;

import com.example.final_workshop.entity.Product;
import com.example.final_workshop.entity.Product_;
import com.example.final_workshop.exceptions.NotFoundException;
import com.example.final_workshop.repository.ProductRepo;
import com.example.final_workshop.search.SearchParams;
import com.example.final_workshop.security.SecUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface{
    private final ProductRepo productRepo;
    @Override
    public ResponseEntity<Product> addProduct(Product product, SecUser user) {
        product.setAddedBy(user.getUsername());
        product.setUpdatedBy(user.getUsername());
        product.setEanCode(Product.EanGenerator.generateEan());
        var productId = productRepo.findByEanCode(product.getEanCode());
        if(productId.isPresent()) {
            while(productId.isPresent()) {
                product.setEanCode(Product.EanGenerator.generateEan());
            }
        }
        productRepo.save(product);

        var location = UriComponentsBuilder.fromPath("/products").buildAndExpand(product.getEanCode()).toUri();
        return ResponseEntity.created(location).body(product);
    }

    @Override
    public Page<Product> getProductsList(SearchParams params) {
        return productRepo.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if(params.getProductName() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Product_.PRODUCT_NAME), params.getProductName()));
            }
            if(params.getCategoryId() != null) {
                predicate = cb.and(predicate, cb.equal(root.get(Product_.CATEGORY_ID), params.getCategoryId()));
            }
            if(params.getMakerId() != null) {
                predicate = cb.and(cb.equal(root.get(Product_.MAKER_ID), params.getMakerId()));
            }
            if(params.getSellPrice() != null) {
                predicate = cb.and(cb.equal(root.get(Product_.SELL_PRICE), params.getSellPrice()));
            }
            if(params.getDateFrom() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Product_.addetAt), params.getDateFrom()));
            }

            return predicate;
        }, PageRequest.of(params.getPage(), params.getPageSize()));
    }

    @Override
    public Product findWithCode(String code) {
        return productRepo.findByEanCode(code).orElseThrow(()-> new NotFoundException("Id Not Found"));
    }


}
