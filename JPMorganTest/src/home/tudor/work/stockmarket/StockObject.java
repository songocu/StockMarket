package home.tudor.work.stockmarket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class StockObject {
	private String stockSymbol;
	private StockType stockType;
	private int lastDivident;
	private int fixedDividend;
	private int parValue;
	
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getLastDivident() {
		return lastDivident;
	}

	public void setLastDivident(int lastDivident) {
			this.lastDivident=lastDivident;
	}

	public int getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(int fixedDividend) {
		switch (stockType) {
		case COMMON:
			this.fixedDividend = 0;
			break;
		case PREFERRED:
			this.fixedDividend = fixedDividend;
			break;
		default :
			this.fixedDividend=0;
			break;
		}
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(int parValue) {
		this.parValue = parValue;
	}

	// @Enumerated(EnumType.STRING)
	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

}
