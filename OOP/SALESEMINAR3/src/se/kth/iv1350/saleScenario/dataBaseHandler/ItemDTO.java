package se.kth.iv1350.saleScenario.dataBaseHandler;

public class ItemDTO
{
	private final String itemName;
	private final double itemPrice;
	/**
	 * Creates an item.
	 * @param itemName Name of the item.
	 * @param itemPrice Price of the item.
	 */
	public ItemDTO(String itemName, double itemPrice)
	{
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	
	public String getName()
	{
		return this.itemName;
	}
	
	public double getPrice()
	{
		return this.itemPrice;
	}
}
