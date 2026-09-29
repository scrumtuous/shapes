package com.mcnz.store.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mcnz.store.Purchase;
import com.mcnz.store.PurchaseStatus;
import com.mcnz.store.messaging.StoreProducers;
import com.mcnz.store.service.StoreService;

/** Small optional API for submitting purchases and seeing the real saved data. */
@RestController
@RequestMapping("/queue")
public class PurchaseController {

    @Autowired
    private StoreProducers producers;

    @Autowired
    private StoreService storeService;

    @PostMapping("/purchases")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long submit(@RequestBody Purchase purchase) {
        Purchase pendingPurchase = storeService.createPendingPurchase(purchase);
        producers.submit(pendingPurchase.id);
        return pendingPurchase.id;
    }

    @GetMapping("/purchases/{purchaseId}/status")
    public PurchaseStatus status(@PathVariable Long purchaseId) {
        return storeService.getPurchaseStatus(purchaseId);
    }
}