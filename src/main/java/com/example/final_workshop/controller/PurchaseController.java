package com.example.final_workshop.controller;

import com.example.final_workshop.entity.Purchase;
import com.example.final_workshop.security.SecUser;
import com.example.final_workshop.service.purchase.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('MANAGER')")
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("/products/{code}/purchase")
    public String purchase(@PathVariable String code, @RequestBody Purchase purchase, @AuthenticationPrincipal SecUser user) {
        return purchaseService.makePurchase(purchase, code, user);
    }

    @GetMapping("/purchases")
    public List<Purchase> getListOfPurchases() {
        return purchaseService.getListOfPurchases();
    }

}
