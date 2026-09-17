package com.mcnz.mm;

public class OrderProcessor {

	public static OrderResponse send(OrderRequest orderRequest) {

		if (clientHasInSuffientFunds(orderRequest)) {
			return new OrderResponse(418, "User is a teapot.");
		}
		
		if (stockLacksLiquidity(orderRequest)) {
			return new OrderResponse(422, "Stock Lacks Liquidity.");
		}
		
		orderRequest.client.updateHoldings(orderRequest.asset, orderRequest.quantity, orderRequest.buySide);
		return new OrderResponse(200, "Success");
	}
	
	private static boolean clientHasInSuffientFunds(OrderRequest orderRequest) {
		return false;
	}
	
	private static boolean stockLacksLiquidity(OrderRequest orderRequest) {
		return false;
	}

}
