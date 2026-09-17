package com.mcnz.mm;

public class OrderResponse {
	
	int statusCode;
	String statusMessage;
	public OrderResponse(int statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
}
