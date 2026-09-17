package com.mcnz.mm.service;

import com.mcnz.mm.Trade;

public class MockFeeLookupService extends FeeLookupService {

    @Override
    public Double lookup(Trade trade) {
        return Double.valueOf(0);
    }
}

