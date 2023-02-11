package com.example.final_workshop.service.sale;

import com.example.final_workshop.entity.Sale;
import com.example.final_workshop.entity.Sale_;
import com.example.final_workshop.exceptions.NotFoundException;
import com.example.final_workshop.repository.ProductRepo;
import com.example.final_workshop.repository.SaleRepo;
import com.example.final_workshop.search.SearchParams;
import com.example.final_workshop.security.SecUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService implements SaleServiceInterface{
    private final SaleRepo saleRepo;
    private final ProductRepo productRepo;

    @Override
    public ResponseEntity<Sale> makeSale(String eanCode, Sale sale, SecUser user) {
        var product = productRepo.findByEanCode(eanCode)
                .orElseThrow(() -> new NotFoundException("Product Id Does Not Exist"));
        sale.setSellPrice(product.getSellPrice());
        sale.setProductId(eanCode);
        sale.setSelledBy(user.getUsername());
        saleRepo.save(sale);
        product.setRemaining(product.getRemaining() - sale.getQuantity());
        productRepo.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @Override
    public Page<Sale> getSalesList(SearchParams params) {
        return saleRepo.findAll((root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if(params.getDateFrom() != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get(Sale_.SELL_DATE), params.getDateFrom()));
            }
            if(params.getDateTo()!=null) {
                predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get(Sale_.SELL_DATE), params.getDateTo()));
            }
            return predicate;
        }, PageRequest.of(params.getPage(), params.getPageSize()));
    }
}
