package com.mcnz.mm.service;

import com.mcnz.mm.Asset;

public class FeeLookupService extends LookupService<Double, Asset> {

    @Override
    public Double lookup(Asset asset) {
        return Double.valueOf(0);
    }
}
