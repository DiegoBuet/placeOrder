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

    @GetMapping({"/list_purchase/new2"}) // This is the route for the credit card form
    public String showCreditCardForm(Model model, HttpSession session) {
        // Get the totalGeneral stored in the session
        Double totalGeneral = (Double) session.getAttribute("totalSum");

        // Check if totalGeneral is not null (in case the user accesses this page directly)
        if (totalGeneral != null) {
            model.addAttribute("totalSum", totalGeneral);
        } else {
            // Handle the case where totalGeneral is null
        }

        // Create an instance of CreditCard with a fixed card number and initial balance
        CreditCard creditCard = new CreditCard();
        creditCard.setBalance(1000.0); // Set an initial balance of 1000.0 (or the desired value)

        model.addAttribute("creditCard", creditCard);
        return "card_operation"; // Redirects to the credit card form
    }

    @PostMapping({"/list_purchase/new2/save"})
    public String processPaymentForm(@ModelAttribute("creditCard") CreditCard creditCard,
                                     @RequestParam("totalSum") Double totalPurchase, Model model) {
        // Validar los números de tarjeta y el saldo antes de procesar el pago
/*        if (isValidCreditCard(creditCard.getCardNumber(), totalPurchase) && creditCard.getBalance() >= totalPurchase) {
            // Procesar el pago (deducir el total del saldo de la tarjeta)
            creditCard.setBalance(creditCard.getBalance() - totalPurchase);

            // Puedes guardar la compra en la base de datos si es necesario

            // Redirigir a la página de pago exitoso
            model.addAttribute("paymentSuccessful", true);
            return "payment_successful";
        } else {
            // Saldo insuficiente o tarjeta inválida, muestra un mensaje de error
            model.addAttribute("paymentError", true);
            return "redirect:/payment_successful";
        }*/
        return "payment_successful";
    }

/*    private boolean isValidCreditCard(String creditCardNumber, Double totalPurchase) {
        // Validar el número de tarjeta de crédito
        if (!creditCardNumber.startsWith("4000")) {
            return false; // El número de tarjeta no comienza con 4000
        }

        // Validar el rango del número de tarjeta de crédito
        int cardNumber = Integer.parseInt(creditCardNumber);
        if (cardNumber < 4111 || cardNumber > 4222) {
            return false; // El número de tarjeta no está en el rango permitido
        }

        // Validar el monto total
        if (totalPurchase <= 0.0) {
            return false; // El monto total no es mayor que cero
        }

        return true; // Todas las validaciones pasaron
    }*/

}
