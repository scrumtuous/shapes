package com.mcnz.store;

import java.util.ArrayList;
import java.util.List;



public class Customer {


    public Long id;

    public String name;

    
    public Address address;


    public List<Purchase> purchases = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.customer = this;
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
        purchase.customer = null;
    }
}
