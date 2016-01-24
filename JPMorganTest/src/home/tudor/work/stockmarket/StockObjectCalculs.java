package home.tudor.work.stockmarket;

import java.util.HashMap;

public class StockObjectCalculs {
	private double price;
	private String stockObjectSymbol;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStockObjectSymbol() {
		return stockObjectSymbol;
	}
	
	public double dividendYield(double price,String stockObjectSymbol) throws Exception{
		if(price==0){
			throw new Exception("Price for the stock can not be 0. Please provide another value",new Throwable());
		}
		if(price<=0){
			throw new Exception("Price can not be negative.", new Throwable());
		}
		HashMap<String, StockObject> stockObjectMap = StockObjectRecorder.getStockObjectSingleton().getStockObjectMap();
		//because value is used often in next lines I preferred to have a local variable to not take each time the same object
		StockObject stockObject=stockObjectMap.get(stockObjectSymbol);
		if(stockObjectMap.containsKey(stockObjectSymbol)){
			if(stockObject.getStockType().equals(StockType.COMMON)){
				return stockObject.getLastDivident()/price;
			}			
			return (stockObject.getFixedDividend()*stockObject.getParValue() )/price;
		} else {
			System.out.println("The StockSymbol: "+ stockObjectSymbol +" does not exists in database.Please provide a valid one");
			return -1;
		}
	}
	
	public double peRatio(double price, String stockObjectSymbol) throws Exception{
		HashMap<String, StockObject> stockObjectMap = StockObjectRecorder.getStockObjectSingleton().getStockObjectMap();
		StockObject stockObject=stockObjectMap.get(stockObjectSymbol);
		if(stockObject.getLastDivident()==0){
			throw new Exception("Dividend for "+stockObjectSymbol+" is null.",new Throwable());
		}
		return price/stockObject.getLastDivident();
		
	}
	
}
