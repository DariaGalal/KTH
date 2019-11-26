package se.kth.iv1350.saleScenario.model;

import se.kth.iv1350.saleScenario.dataBaseHandler.ItemDTO;
/**
 * Collects information about the items scanned and the finishing sale.
 */
public class SaleInformation 
{
	private final String itemName;
	private final double itemPrice;
	private final double runningTotal;
	private final double totalWithTax;
	/**
	 * Creates an instance containing informations about a particular sale to be displayed to the view.
	 * @param lastScannedItem
	 * @param runningTotal
	 * @param total
	 * @param totalWithTax
	 */
	public SaleInformation(ItemDTO lastScannedItem, double runningTotal, double totalWithTax)
	{
		this.itemName = lastScannedItem.getName();
		this.itemPrice = lastScannedItem.getPrice();
		this.runningTotal = runningTotal;
		this.totalWithTax = totalWithTax;
	}
	
	public double getRunningTotal()
	{
		return this.runningTotal;
	}
	
	public double getItemPrice()
	{
		return this.itemPrice;
	}
	
	public String getItemName()
	{
		return this.itemName;
	}

}
