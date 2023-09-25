package com.app.web.controller;

import com.app.web.entity.Purchase;

import com.app.web.service.PurchaseService;
import com.app.web.service.StockProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @GetMapping({"/list_purchase/new"})
    public String showPurchaseRegistrationForm(Model model){
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        return "create_purchase"; // Redirects to the purchase list page
    }

    @PostMapping({"/list_purchase"})
    public String savePurchase(@ModelAttribute("purchase") Purchase purchase, Model model) {

        if (isValidProductCode(purchase.getProductCode())) {
            Double totalPurchase = purchase.getPrice() * purchase.getQuantity();
            purchase.setTotal(totalPurchase);
            service.savePurchase(purchase);
            return "redirect:/list_purchase";
        } else {
            model.addAttribute("productCodeError", true);
            return "create_purchase";
        }
    }

    @GetMapping({"/list_purchase/edit/{id}"})
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("purchase", service.getPurchaseById(id));
        return "edit_purchase";
    }
  @PostMapping({"/list_purchase/{id}"})
  public String updatePurchase(@PathVariable Long id, @ModelAttribute("purchase") Purchase purchase, Model model) {

      if (isValidProductCode(purchase.getProductCode())) {

          Purchase currentPurchase = service.getPurchaseById(id);

          currentPurchase.setProduct(purchase.getProduct());
          currentPurchase.setPrice(purchase.getPrice());
          currentPurchase.setQuantity(purchase.getQuantity());
          currentPurchase.setProductCode(purchase.getProductCode());

          Double totalPurchase = purchase.getPrice() * purchase.getQuantity();
          currentPurchase.setTotal(totalPurchase);

          service.updatePurchase(currentPurchase);

          return "redirect:/list_purchase";
      } else {
          model.addAttribute("productCodeError", true);
          model.addAttribute("purchase", purchase);
          return "edit_purchase";
      }
  }


    @GetMapping({"/list_purchase/{id}"})
    public String deletePurchase(@PathVariable Long id){
        service.deletePurchase(id);
        return "redirect:/list_purchase"; // Redirects to the purchase list page
    }

    @GetMapping({"/list_purchase","/"})
    public String listPurchases(Model model, HttpSession session){
        List<Purchase> purchaseList = service.listPurchases();
        Double totalSum = purchaseList.stream()
                .mapToDouble(purchase -> purchase.getTotal())
                .sum();
        session.setAttribute("totalSum", totalSum);
        model.addAttribute("list_purchase", purchaseList);
        return "list_purchase";
    }

    private boolean isValidProductCode(Integer productCode) {
        try {
            Integer code = productCode;
            int aux =  code.toString().length();
            return code <= 239 && aux==3 || code >= 384 && aux==3;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}