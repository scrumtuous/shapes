package com.mcnz.mm;

import com.mcnz.mm.service.ClientLookupService;
import com.mcnz.mm.service.PriceLookupService;

public class SettlementEngine {
	
	static ClientLookupService clientLookupService = new ClientLookupService();
	static PriceLookupService priceLookupService = new PriceLookupService();

	public static void main(String[] args) {
		Client client = clientLookupService.lookup("Alice Chen");
		Asset asset = new Asset("AAPL", AssetClass.EQUITY);
		OrderRequest orderRequest = new OrderRequest(client, asset, 100, true);
		OrderResponse response = OrderProcessor.send(orderRequest);

	}

}
