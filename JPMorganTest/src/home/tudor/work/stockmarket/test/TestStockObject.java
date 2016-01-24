package home.tudor.work.stockmarket.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import home.tudor.work.stockmarket.*;

public class TestStockObject {
	
	@Before
	public void cleanTheStockObjectRecorder(){
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		stockObjectRecorder.clearStockObjectRecoder();
	}
	
	@Test
	public void testThatAnObjectWasAddedToTheMap() throws Exception{
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		StockObject stockObject=new StockObject();
		stockObject.setStockSymbol("TEA");
		stockObject.setStockType(StockType.COMMON);
		stockObject.setLastDivident(0);
		stockObject.setParValue(100);
		
		stockObjectRecorder.addStockObjectToMap(stockObject);
		
		assertTrue(stockObjectRecorder.getStockObjectMap().size()>0);
	}
	@Test
	public void testSetterOfFixedDividentDependingOnType() throws Exception{
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		assertTrue(stockObjectRecorder.clearStockObjectRecoder());
		StockObject stockObject=new StockObject();
		stockObject.setStockSymbol("TEA");
		stockObject.setStockType(StockType.COMMON);
		stockObject.setLastDivident(10);
		stockObject.setFixedDividend(20);
		stockObject.setParValue(100);
		
		stockObjectRecorder.addStockObjectToMap(stockObject);
		
		//Test that the added value in the FixedDividdent is 0, even the value from setter is 20
		assertTrue(stockObjectRecorder.getStockObjectMap().get("TEA").getFixedDividend()==0);
	}
	
	@Test (expected = Exception.class)
	public void testThatAnExceptionIsThrowIfWeIntroduceMultipleSameTypeObjects() throws Exception{
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		
		StockObject stockObject1=new StockObject();
		stockObject1.setStockSymbol("TEA");
		stockObject1.setStockType(StockType.COMMON);
		stockObject1.setLastDivident(10);
		stockObject1.setFixedDividend(20);
		stockObject1.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject1);
		
		StockObject stockObject2=new StockObject();
		stockObject2.setStockSymbol("TEA");
		stockObject2.setStockType(StockType.COMMON);
		stockObject2.setLastDivident(10);
		stockObject2.setFixedDividend(20);
		stockObject2.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject2);
		
		
	}
	
	@Test
	public void testNormalDividendYieldCasesOfDividendYield() throws Exception{
		StockObjectCalculs stockObjectCalculs=new StockObjectCalculs();
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		
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
		
		
		
		//The reason why we have like a parameter a String Symbol and not the object itself
		//is because, in a GUI, the user will introduce the String not an object
		assertTrue(stockObjectCalculs.dividendYield(50, "TEA")==0.2);
		assertTrue(stockObjectCalculs.dividendYield(50, "GIN")==4.0);
		assertTrue(stockObjectCalculs.dividendYield(50, "POP")==0.16);
		
	}
	
	
	//Test use verified exception. Verify that the text is the expected one, not thrown by another Exception 
	@Rule public ExpectedException thrown1= ExpectedException.none();
	@Rule public ExpectedException thrown2= ExpectedException.none();
	@Test 
	public void testPriceCanNotBeZeroOrNegative() throws Exception {
	    
		StockObjectCalculs stockObjectCalculs=new StockObjectCalculs();
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		
		StockObject stockObject1=new StockObject();
		stockObject1.setStockSymbol("GIN");
		stockObject1.setStockType(StockType.PREFERRED);
		stockObject1.setLastDivident(10);
		stockObject1.setFixedDividend(2);
		stockObject1.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject1);
		thrown3.expect( Exception.class );
		thrown3.expectMessage("Price for the stock can not be 0");
		stockObjectCalculs.dividendYield(0, "GIN");
		
		thrown2.expect( Exception.class );
		thrown2.expectMessage("Price can not be negative");
		stockObjectCalculs.dividendYield(-1, "GIN");
	}
	
	@Test
	public void testNormalCalculForPeRartion() throws Exception{
		StockObjectCalculs stockObjectCalculs=new StockObjectCalculs();
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		
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
		
		
		
		//The reason why we have like a parameter a String Symbol and not the object itself
		//is because, in a GUI, the user will introduce the String not an object
		assertTrue(stockObjectCalculs.peRatio(50, "TEA")==5.0);
		assertTrue(stockObjectCalculs.peRatio(50, "GIN")==5.0);
		assertTrue(stockObjectCalculs.peRatio(50, "POP")==6.25);
	}
	
	@Rule public ExpectedException thrown3= ExpectedException.none();
	@Test 
	public void testPeRatioCalculDividendThrowException() throws Exception {
	    
		StockObjectCalculs stockObjectCalculs=new StockObjectCalculs();
		StockObjectRecorder stockObjectRecorder=StockObjectRecorder.getStockObjectSingleton();
		
		StockObject stockObject1=new StockObject();
		stockObject1.setStockSymbol("GIN");
		stockObject1.setStockType(StockType.PREFERRED);
		stockObject1.setLastDivident(0);
		stockObject1.setFixedDividend(2);
		stockObject1.setParValue(100);
		stockObjectRecorder.addStockObjectToMap(stockObject1);
		thrown3.expect( Exception.class );
		thrown3.expectMessage("Dividend for");
		stockObjectCalculs.peRatio(0, "GIN");
	}
}
