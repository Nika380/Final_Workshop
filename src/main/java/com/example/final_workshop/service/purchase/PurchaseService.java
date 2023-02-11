package com.example.final_workshop.service.purchase;

import com.example.final_workshop.entity.Purchase;
import com.example.final_workshop.exceptions.NotFoundException;
import com.example.final_workshop.repository.ProductRepo;
import com.example.final_workshop.repository.PurchaseRepo;
import com.example.final_workshop.security.SecUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService implements PurchaseServiceInterface{
    private final PurchaseRepo purchaseRepo;
    private final ProductRepo productRepo;
    @Override
    public String makePurchase(Purchase purchase, String code, SecUser user) {

        var product = productRepo.findByEanCode(code).orElseThrow(() -> new NotFoundException("Product Id Does not Exist"));
        purchase.setProductId(code);
        product.setRemaining(product.getRemaining() + purchase.getQuantity());
        purchase.setPurchasedBy(user.getUsername());

        purchaseRepo.save(purchase);
        return "Purchase Was Succesfull \n" + "You bought: " + purchase.getQuantity()
                + " " + product.getProductName() + "\n"
                + "You Have " + product.getRemaining() + " in inventory";
    }

    @Override
    public List<Purchase> getListOfPurchases() {
        return purchaseRepo.findAll();
    }
}
