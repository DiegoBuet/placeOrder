package com.app.web.service;

import com.app.web.entity.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> listPurchases();
    Purchase savePurchase(Purchase purchase);
    Purchase getPurchaseById(Long id);
    Purchase updatePurchase(Purchase purchase);
    void deletePurchase(Long id);

}
