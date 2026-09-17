package com.mcnz.mm.service;

public class PriceLookupService extends LookupService<Double, String> {

    @Override
    public Double lookup(String name) {
        return Double.valueOf(0);
    }
}