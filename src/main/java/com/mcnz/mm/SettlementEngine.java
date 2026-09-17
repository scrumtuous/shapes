package com.mcnz.mm;

import com.mcnz.mm.service.*;
public class SettlementEngine {
	
	static ClientLookupService clientLookupService = new MockClientLookupService();
	static PriceLookupService priceLookupService = new MockPriceLookupService();

	public static void main(String[] args) {
		Client client = clientLookupService.lookup("Alice Chen");
		Asset asset = new Asset("AAPL", AssetClass.EQUITY);
		OrderRequest orderRequest = new OrderRequest(client, asset, 100, true);
		OrderResponse response = OrderProcessor.send(orderRequest);

	}
}
