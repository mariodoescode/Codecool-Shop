package com.codecool.shop.model;


import lombok.Getter;
import lombok.Setter;

import java.io.*;


@Getter
@Setter
public class Order {
    private int id;
    private String name;
    private String email;
    private String phoneNumber;
    private String billingAddress;
    private String billingCountry;
    private String billingCity;
    private String billingZipCode;
    private String shippingAddress;
    private String shippingCountry;
    private String shippingCity;
    private String shippingZipCode;


    public Order(String name, String phoneNumber, String email,
                 String billingAddress,String billingCountry,String billingCity,
                 String billingZipCode,String shippingAddress,String shippingCountry,
                 String shippingCity, String shippingZipCode) {
        this.name = name;
        this.billingAddress = billingAddress;
        this.billingCity= billingCity;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingCountry = billingCountry;
        this.billingZipCode = billingZipCode;
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.shippingCountry = shippingCountry;
        this.shippingZipCode = shippingZipCode;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

}
