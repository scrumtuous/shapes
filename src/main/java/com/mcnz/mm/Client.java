package com.mcnz.mm;

import com.mcnz.mm.service.FeeLookupService;
import com.mcnz.mm.service.PriceLookupService;

public class Client {
	
	public String id;
	public String name;
	public Portfolio portfolio = new Portfolio();
	static PriceLookupService priceLookupService;
	static FeeLookupService feeLookupService;
	
    public Client() {}

    public Client(String id, String name, double cashHoldings) {

        this.id = id;
        this.name = name;

        Asset cash = new Asset("USD", AssetClass.CASH);

        Holding holding = new Holding(cash, (int)cashHoldings);

        portfolio.holdings.add(holding);
    }
	
	public void updateHoldings(Asset asset, int quantity, boolean buy) {
		
		if (buy) {
			Holding holding = new Holding(asset, quantity);
			portfolio.holdings.add(holding);
			double price = priceLookupService.lookup(asset.symbol);
			double fee = feeLookupService.lookup(null);
			double totalCost = (price * quantity) + fee;
			portfolio.reduceCashHoldings(totalCost);
			
		}
		
	}

}
