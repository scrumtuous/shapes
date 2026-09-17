package com.mcnz.mm;

public class OrderResponse {
	
	int statusCode;
	String statusMessage;
	public OrderResponse(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
}
