package com.mcnz.mm;

import java.util.*;

public class Portfolio {
	
	public List<Holding> holdings = new ArrayList<Holding>();
	
	public List<Asset> getEquityHoldings(){return null;}
	public List<Asset> getCryptoHoldings(){return null;}
	public List<Asset> getBondHoldings()  {return null;}
	public List<Asset> getCashHoldings()  {return null;}
	
	public double getTotalAssetValues()   {return 0;}
	public double getValueOfCashHoldings() { return 0; }
	
	public void updateHoldings(Asset asset, int quantity, boolean buy) { }
	public void reduceCashHoldings(double dollarAmount) { } 
	public void increaseCashHoldings(double dollarAmount) { } 

}
