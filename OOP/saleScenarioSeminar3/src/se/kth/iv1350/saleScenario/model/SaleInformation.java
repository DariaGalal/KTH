package se.kth.iv1350.salScenario.model;

import se.kth.iv1350.salScenario.dataBaseHandler.ItemDTO;

public class SaleInformation
{
	private final String itemName;
	private final double itemPrice;
	private final double runningTotal;
	private final double total;
	private final double totalWithTax;
	/**
	 * Creates an instance containing informations about a particular sale to be displayed to the view.
	 * @param lastScannedItem
	 * @param runningTotal
	 * @param total
	 * @param totalWithTax
	 */
	public SaleInformation(ItemDTO lastScannedItem, double runningTotal, double total, double totalWithTax)
	{
		this.itemName = lastScannedItem.getName();
		this.itemPrice = lastScannedItem.getPrice();
		this.runningTotal = runningTotal;
		this.total = total;
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
	
	public double getTotal()
	{
		return this.total;
	}
	
	public String getItemName()
	{
		return this.itemName;
	}

}
