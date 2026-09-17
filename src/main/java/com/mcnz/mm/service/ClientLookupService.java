package com.mcnz.mm.service;

import com.mcnz.mm.Asset;
import com.mcnz.mm.AssetClass;
import com.mcnz.mm.Client;
import com.mcnz.mm.Holding;

public class ClientLookupService extends LookupService<Client, String> {

    @Override
    public Client lookup(String name) {
    	
    	Asset cash = new Asset("USD", AssetClass.CASH);
    	Holding holding = new Holding(cash, 1_000_000);
    	Client client = new Client();
    	client.name = name;
    	client.portfolio.holdings.add(holding);
        return client;
    }
}