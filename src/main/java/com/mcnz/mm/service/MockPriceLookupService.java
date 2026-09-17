package com.mcnz.mm.service;

import java.util.HashMap;
import java.util.Map;

public class MockPriceLookupService extends PriceLookupService {

    private final Map<String, Double> prices = new HashMap<>();

    public MockPriceLookupService() {
        prices.put("AAPL",  189.25);
        prices.put("MSFT",  410.50);
        prices.put("GOOGL", 144.50);
        prices.put("TSLA",  244.75);
        prices.put("VUSA",   85.60);
        prices.put("US10Y",  98.75);
        prices.put("BTC", 46200.00);
        prices.put("ETH",  2695.00);
    }

    @Override
    public Double lookup(String symbol) {
        return prices.get(symbol);
    }
}