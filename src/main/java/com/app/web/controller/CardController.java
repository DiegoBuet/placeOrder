package com.app.web.controller;

import com.app.web.entity.CreditCard;
import com.app.web.service.PurchaseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CardController {
    @Autowired
    private PurchaseService service;
    private CreditCard creditCard;

    @GetMapping("/list_purchase/new2")
    public String showCreditCardForm(Model model, HttpSession session) {
        Double totalGeneral = (Double) session.getAttribute("totalSum");

        if (totalGeneral != null) {
            model.addAttribute("totalSum", totalGeneral);
        } else {

        }

        creditCard = new CreditCard();
        creditCard.setBalance(1000.0);

        model.addAttribute("creditCard", creditCard);
        return "card_operation";
    }

@PostMapping("/list_purchase/new2/save")
public String processPaymentForm(@ModelAttribute("creditCard") CreditCard creditCard,
                                 @RequestParam("totalSum") Double totalPurchase, Model model) {

    if (isValidCreditCard(creditCard.getCardNumber(), totalPurchase)) {

        creditCard.setBalance(creditCard.getBalance() - totalPurchase);

        model.addAttribute("totalSum", totalPurchase);

        model.addAttribute("paymentSuccessful", true);
        return "payment_successful";
    } else {
        // Saldo insuficiente o tarjeta inválida, muestra un mensaje de error
        model.addAttribute("paymentError", true);
        return "payment_error";
    }
}


    private boolean isValidCreditCard(Integer creditCardNumber, Double totalSum) {

        try {
            if (creditCardNumber < 4111 || creditCardNumber > 4222) {
                return false;
            }

            if (totalSum <= 0.0 || totalSum > creditCard.getBalance()) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
