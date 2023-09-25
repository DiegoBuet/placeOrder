package com.app.web.entity;

import lombok.Data;

@Data
public class CreditCard {
    private Integer cardNumber;
    private Double balance =1000.0;

}
