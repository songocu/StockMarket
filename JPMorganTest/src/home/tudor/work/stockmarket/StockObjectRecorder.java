package home.tudor.work.stockmarket;

import java.util.HashMap;

public class StockObjectRecorder {
	private HashMap<String, StockObject> stockObjectMap=new HashMap<String,StockObject>();
	private static StockObjectRecorder instance;
	
	private StockObjectRecorder(){
		
	}
	
	//I prefer a singleton object because I prefer to have only one map with StackObjects
	public static StockObjectRecorder getStockObjectSingleton(){
		if(instance==null){
			instance=new StockObjectRecorder();
			return instance;
		} else {
			return instance;
		}
	}
	
	public boolean addStockObjectToMap(StockObject aStockObject) throws Exception{
		if(stockObjectMap.containsKey(aStockObject.getStockSymbol())){
			throw new Exception("StockObject with key:" + aStockObject.getStockSymbol() + " already exists. Introduce another one", new Throwable());
		} else {
			stockObjectMap.put(aStockObject.getStockSymbol(), aStockObject);
			return true;
		}
	}

	public HashMap<String, StockObject> getStockObjectMap() {
		return stockObjectMap;
	}
	
	public boolean clearStockObjectRecoder(){
		instance.getStockObjectMap().clear();
		if(instance.getStockObjectMap().size()==0) return true;
		return false;
	}
	
}
