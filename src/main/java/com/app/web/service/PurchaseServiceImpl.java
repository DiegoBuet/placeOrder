package com.app.web.service;

import com.app.web.entity.Purchase;
import com.app.web.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository repository;

    @Override
    public List<Purchase> listPurchases() {
        return repository.findAll();
    }

    @Override
    public Purchase savePurchase(Purchase purchase) {
        return repository.save(purchase);
    }

    @Override
    public Purchase getPurchaseById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Purchase updatePurchase(Purchase purchase) {
        return repository.save(purchase);
    }

    @Override
    public void deletePurchase(Long id) {
        repository.deleteById(id);
    }
}