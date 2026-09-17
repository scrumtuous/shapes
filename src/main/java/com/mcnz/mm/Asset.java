package com.mcnz.mm;

public class Asset {
	
	String symbol;
	AssetClass assetClass;
	
	public Asset(String symbol, AssetClass assetClass) {
		this.assetClass = assetClass;
		this.symbol = symbol;
	}

}
