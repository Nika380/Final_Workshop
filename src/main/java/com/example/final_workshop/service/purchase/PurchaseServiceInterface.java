package com.example.final_workshop.service.purchase;

import com.example.final_workshop.entity.Purchase;
import com.example.final_workshop.security.SecUser;

import java.util.List;

public interface PurchaseServiceInterface {
    String makePurchase(Purchase purchase, String eanCode, SecUser user);
    List<Purchase> getListOfPurchases();
}
