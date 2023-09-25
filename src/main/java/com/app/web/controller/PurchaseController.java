package com.app.web.controller;

import com.app.web.entity.Purchase;

import com.app.web.service.PurchaseService;
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
 /*   @Autowired
    private ProductStockService stockService;*/

    @GetMapping({"/list_purchase/new"})
    public String showPurchaseRegistrationForm(Model model){
        Purchase purchase = new Purchase();
        model.addAttribute("purchase", purchase);
        return "create_purchase"; // Redirects to the purchase list page
    }

    @PostMapping({"/list_purchase"})
    public String savePurchase(@ModelAttribute("purchase") Purchase purchase, Model model) {
        // Validar que el código de producto esté dentro del rango permitido (239 - 384)
        if (isValidProductCode(purchase.getProductCode())) {
            // Calculate the total for the new purchase
            Double totalPurchase = purchase.getPrice() * purchase.getQuantity();
            purchase.setTotal(totalPurchase);

            // Save the purchase in the database
            service.savePurchase(purchase);

            return "redirect:/list_purchase";
        } else {
            // El código de producto no está dentro del rango permitido, muestra un mensaje de error
            model.addAttribute("productCodeError", true);
            return "create_purchase"; // Vuelve al formulario de creación con un mensaje de error
        }
    }

    @GetMapping({"/list_purchase/edit/{id}"})
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("purchase", service.getPurchaseById(id));
        return "edit_purchase"; // Redirects to the purchase list page
    }


    @PostMapping({"/list_purchase/{id}"})
    public String updatePurchase(@PathVariable Long id, @ModelAttribute("purchase") Purchase purchase, Model model) {
        // Validar que el código de producto esté dentro del rango permitido (239 - 384)
        if (isValidProductCode(purchase.getProductCode())) {
            // Get the current purchase
            Purchase currentPurchase = service.getPurchaseById(id);

            // Update the fields of the purchase with the new values
            currentPurchase.setProduct(purchase.getProduct());
            currentPurchase.setPrice(purchase.getPrice());
            currentPurchase.setQuantity(purchase.getQuantity());
            currentPurchase.setProductCode(purchase.getProductCode());

            // Calculate the total for the updated purchase
            Double totalPurchase = purchase.getPrice() * purchase.getQuantity();
            currentPurchase.setTotal(totalPurchase);

            // Update the purchase in the database
            service.updatePurchase(currentPurchase);

            return "redirect:/list_purchase";
        } else {
            // El código de producto no está dentro del rango permitido, muestra un mensaje de error
            model.addAttribute("productCodeError", true);
            return "edit_purchase"; // Vuelve al formulario de edición con un mensaje de error
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

        // Calculate the total sum
        Double totalSum = purchaseList.stream()
                .mapToDouble(purchase -> purchase.getTotal())
                .sum();

        // Almacena el valor de totalSum en la sesión del usuario
        session.setAttribute("totalSum", totalSum);

        model.addAttribute("list_purchase", purchaseList);

        return "list_purchase"; // Redirects to the purchase list page
    }

    private boolean isValidProductCode(Integer productCode) {
        try {
            Integer code = productCode;
            return code <= 239 || code >= 384;
        } catch (NumberFormatException e) {
            return false; // El código no es un número válido
        }
    }

}