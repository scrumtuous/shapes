package com.mcnz.store;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Purchase {

    
    public Long id;

    public LocalDate date;

    
    public Customer customer;


    public List<Product> products = new ArrayList<>();

    public Purchase() {
    }

    public Purchase(LocalDate date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.purchase = this;
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.purchase = null;
    }
}
