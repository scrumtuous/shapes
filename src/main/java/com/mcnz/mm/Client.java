package com.mcnz.mm;

import com.mcnz.mm.service.FeeLookupService;
import com.mcnz.mm.service.PriceLookupService;

public class Client {
	
	public String name;
	public Portfolio portfolio = new Portfolio();
	static PriceLookupService priceLookupService;
	static FeeLookupService feeLookupService;
	
	public void updateHoldings(Asset asset, int quantity, boolean buy) {
		
		if (buy) {
			Holding holding = new Holding(asset, quantity);
			portfolio.holdings.add(holding);
			double price = priceLookupService.lookup(asset.symbol);
			double fee = feeLookupService.lookup(asset);
			double totalCost = (price * quantity) + fee;
			portfolio.reduceCashHoldings(totalCost);
		}
		
	}

}
