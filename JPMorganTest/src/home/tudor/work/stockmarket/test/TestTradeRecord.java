package home.tudor.work.stockmarket.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import home.tudor.work.stockmarket.StockObject;
import home.tudor.work.stockmarket.StockObjectRecorder;
import home.tudor.work.stockmarket.StockType;
import home.tudor.work.stockmarket.TradeRecord;
import home.tudor.work.stockmarket.TradeRecordCalculs;
import home.tudor.work.stockmarket.TradeRecordRecorder;
import home.tudor.work.stockmarket.TransactionType;

public class TestTradeRecord {
	
	//for each test I would like to have a clean Map, since is Singleton
	//and after this, populate it with some values
	@Before
	public void cleanAndAddTradeRecordRecorder() throws Exception{
		TradeRecordRecorder tradeRecordRecorder=TradeRecordRecorder.getTradeRecordRecorderSingleton();
		tradeRecordRecorder.clearTradeRecordRecorderMap();
		
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		stockObjectRecorder.clearStockObjectRecoder();
		
		StockObject stockObject1=new StockObject();
		stockObject1.setStockSymbol("GIN");
		stockObject1.setStockType(StockType.PREFERRED);
		stockObject1.setLastDivident(10);
		stockObject1.setFixedDividend(2);
		stockObject1.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject1);
		
		StockObject stockObject2=new StockObject();
		stockObject2.setStockSymbol("POP");
		stockObject2.setStockType(StockType.COMMON);
		stockObject2.setLastDivident(8);
		stockObject2.setFixedDividend(0);
		stockObject2.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject2);
		
		StockObject stockObject3=new StockObject();
		stockObject3.setStockSymbol("TEA");
		stockObject3.setStockType(StockType.COMMON);
		stockObject3.setLastDivident(10);
		stockObject3.setFixedDividend(20);
		stockObject3.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject3);
		
		TradeRecord tradeRecord1=new TradeRecord();
		tradeRecord1.setTradeId(1);
		tradeRecord1.setTradeDate(new Date());
		tradeRecord1.setQuantityOfShares(20);
		tradeRecord1.setTransactionType(TransactionType.SELL);
		tradeRecord1.setTradePrice(10);
		tradeRecord1.setStockObject(stockObject1);
		tradeRecordRecorder.addTradeRecordToMap(tradeRecord1);
		
		TradeRecord tradeRecord2=new TradeRecord();
		tradeRecord2.setTradeId(2);
		tradeRecord2.setTradeDate(new Date());
		tradeRecord2.setQuantityOfShares(50);
		tradeRecord2.setTransactionType(TransactionType.SELL);
		tradeRecord2.setTradePrice(70);
		tradeRecord2.setStockObject(stockObject2);
		tradeRecordRecorder.addTradeRecordToMap(tradeRecord2);
		
		TradeRecord tradeRecord3=new TradeRecord();
		tradeRecord3.setTradeId(3);
		tradeRecord3.setTradeDate(new Date());
		tradeRecord3.setQuantityOfShares(5);
		tradeRecord3.setTransactionType(TransactionType.BUY);
		tradeRecord3.setTradePrice(50);
		tradeRecord3.setStockObject(stockObject3);
		tradeRecordRecorder.addTradeRecordToMap(tradeRecord3);
	}
	
	@Test
	public void testCalculateVolumeWeightedStoc() throws Exception{
		TradeRecordCalculs tradeRecordCalculs=new TradeRecordCalculs();		
		assertTrue(tradeRecordCalculs.volumeWeightedStoc()==52.666666666666664);

	}
	
	//Makes no sense to execute this in a separate Thread, since other tests are fast
	@Test
	public void testAddARecordAfter15MinutesAdnDoTheCalculs() throws Exception{
		TradeRecordRecorder tradeRecordRecorder=TradeRecordRecorder.getTradeRecordRecorderSingleton();
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		//The only record later than 15 Minutes will be the added below
		Thread.sleep(60*1000*15);
		
		StockObject stockObject4=new StockObject();
		stockObject4.setStockSymbol("JOE");
		stockObject4.setStockType(StockType.PREFERRED);
		stockObject4.setLastDivident(10);
		stockObject4.setFixedDividend(2);
		stockObject4.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject4);
		
		TradeRecord tradeRecord4=new TradeRecord();
		tradeRecord4.setTradeId(4);
		tradeRecord4.setTradeDate(new Date());
		tradeRecord4.setQuantityOfShares(20);
		tradeRecord4.setTransactionType(TransactionType.SELL);
		tradeRecord4.setTradePrice(10);
		tradeRecord4.setStockObject(stockObject4);
		tradeRecordRecorder.addTradeRecordToMap(tradeRecord4);
		
		TradeRecordCalculs tradeRecordCalculs=new TradeRecordCalculs();	
		assertTrue(tradeRecordCalculs.volumeWeightedStoc()==10);
	}
	
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testSumException() throws Exception{
		TradeRecordRecorder tradeRecordRecorder=TradeRecordRecorder.getTradeRecordRecorderSingleton();
		tradeRecordRecorder.clearTradeRecordRecorderMap();
		
		thrown.expect( Exception.class );
		thrown.expectMessage("Sum quantity can not be null");
		TradeRecordCalculs tradeRecordCalculs=new TradeRecordCalculs();	
		tradeRecordCalculs.volumeWeightedStoc();
		
	}
	
	@Test
	public void testGeometricMean(){
		
		TradeRecordCalculs tradeRecordCalculs=new TradeRecordCalculs();	
		assertTrue(tradeRecordCalculs.geometricMean()==1);
		
	}
}
