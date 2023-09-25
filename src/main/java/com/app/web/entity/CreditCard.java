package com.app.web.entity;

import lombok.Data;

@Data
public class CreditCard {
    private String cardNumber;
    private Double balance;

   /* public CreditCard() {
    }

    public CreditCard(String cardNumber, Double balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }*/
}
