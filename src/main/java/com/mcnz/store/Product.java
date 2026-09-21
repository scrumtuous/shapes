package com.mcnz.store;



public class Product {


    public Long id;

    public String name;
    public int quantity;
    public double price;

    
    public Purchase purchase;

    public Product() {
    }

    public Product(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
