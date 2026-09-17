package com.mcnz.mm;

public class OrderRequest {

	Asset asset;
	Client client;
	int quantity;
	boolean buySide;
	public OrderRequest(Client client, Asset asset, int quantity, boolean buySide) {
		super();
		this.client = client;
		this.asset = asset;
		this.quantity = quantity;
	}
}
