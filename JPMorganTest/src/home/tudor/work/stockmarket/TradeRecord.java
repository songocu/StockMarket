package home.tudor.work.stockmarket;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class TradeRecord {
	private int tradeId;
	private Date tradeDate;
	private int quantityOfShares;
	private TransactionType transactionType;
	private int tradePrice;
	
	//@OneToMany
	//@JoinColumn(name="stockSymbol")
	private StockObject stockObject;
	
	
	public int getTradeId() {
		return tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public Date getTradeDate() {
		return tradeDate;
	}
	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	public int getQuantityOfShares() {
		return quantityOfShares;
	}
	public void setQuantityOfShares(int quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public int getTradePrice() {
		return tradePrice;
	}
	public void setTradePrice(int tradePrice) {
		this.tradePrice = tradePrice;
	}
	public StockObject getStockObject() {
		return stockObject;
	}
	public void setStockObject(StockObject stockObject) {
		this.stockObject = stockObject;
	}
	
	
}
