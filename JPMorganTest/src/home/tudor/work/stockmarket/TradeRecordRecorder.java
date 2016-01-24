package home.tudor.work.stockmarket;

import java.util.HashMap;

public class TradeRecordRecorder {
	private HashMap<Integer, TradeRecord> tradeRecordMap=new HashMap<Integer,TradeRecord>();
	private volatile static TradeRecordRecorder instance;
	
	private TradeRecordRecorder(){
		
	}
	
	//I want this instance to be singleton and thread-safe because I want when I take something from map,
	//somebody else to add something. This is useful if it will be used in a GUI
	//Normally I am not afraid of dead-lock, since I do not need here to implement something like producer-consumer
	public static TradeRecordRecorder getTradeRecordRecorderSingleton(){
		if(instance==null){
			synchronized (TradeRecordRecorder.class){
				if(instance==null){
					instance=new TradeRecordRecorder();
					return instance;					
				}
			}
		}
		return instance;
	}
	
	public synchronized boolean addTradeRecordToMap(TradeRecord aTradeRecord) throws Exception{
		if(tradeRecordMap.containsKey(aTradeRecord.getTradeId())){
			throw new Exception("TradeRecord with key: "+ aTradeRecord.getTradeId()+" already exists.Please introduce another one",new Throwable());
		} else {
			tradeRecordMap.put(aTradeRecord.getTradeId(), aTradeRecord);
			return true;
		}
	}

	public synchronized HashMap<Integer, TradeRecord> getTradeRecordMap() {
		return tradeRecordMap;
	}
	
	public boolean clearTradeRecordRecorderMap(){
		instance.getTradeRecordMap().clear();
		if(instance.getTradeRecordMap().size()==0) return true;
		return false;
	}
	
}
