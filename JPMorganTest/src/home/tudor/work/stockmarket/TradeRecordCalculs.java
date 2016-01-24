package home.tudor.work.stockmarket;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TradeRecordCalculs {
	private final int DATE_VALUE_TO_DECREASE=-15;
	
	public double volumeWeightedStoc() throws Exception {
		
		// get the map with records
		HashMap<Integer, TradeRecord> tradeRecordMap = TradeRecordRecorder.getTradeRecordRecorderSingleton()
				.getTradeRecordMap();
		// Get the current date
		Date nowDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(nowDate);
		// decrease the current date with 15 minutes
		cal.add(Calendar.MINUTE, DATE_VALUE_TO_DECREASE);
		// initialize the sums with 0
		double sumTradeXQuatity = 0;
		double sumQuantity = 0;

		// a Calendar instance to be used in parsing the map
		Calendar calFromMap = Calendar.getInstance();

		for (Integer aTradeRecordKey : tradeRecordMap.keySet()) {
			Date dateFromMap = tradeRecordMap.get(aTradeRecordKey).getTradeDate();
			calFromMap.setTime(dateFromMap);
			// if the element from map is after the current date -15 Minutes, is
			// in our interest
			if (calFromMap.after(cal)) {
				sumTradeXQuatity = sumTradeXQuatity + tradeRecordMap.get(aTradeRecordKey).getTradePrice()
						* tradeRecordMap.get(aTradeRecordKey).getQuantityOfShares();
				sumQuantity = sumQuantity + tradeRecordMap.get(aTradeRecordKey).getQuantityOfShares();
			}
		}

		if (sumQuantity == 0) {
			throw new Exception("Sum quantity can not be null", new Throwable());
		}

		return sumTradeXQuatity / sumQuantity; // I know that double
												// calculations in Java are not
												// exact. But I suppose that
												// also are not needed to be
												// exact in this example
	}

	public double geometricMean() {
		// get the map with records
		HashMap<Integer, TradeRecord> tradeRecordMap = TradeRecordRecorder.getTradeRecordRecorderSingleton()
				.getTradeRecordMap();
		// Normally, I should use BigDecimal type for exact and big
		// calculations. But I think that a database will not know about this
		// datatype
		// so, for this example I use just double. This observation is valid not
		// only here, of course
		double product = 1;
		int n = 0;
		for (Integer aTradeRecordKey : tradeRecordMap.keySet()) {
			if (tradeRecordMap.get(aTradeRecordKey).getTradePrice() == 0)
				continue;
			n++;
			product = product * tradeRecordMap.get(aTradeRecordKey).getTradePrice();
		}
		return Math.pow(product, 1 / n);
	}

}
