package se.kth.iv1350.salScenario.dataBaseHandler;

import java.util.ArrayList;

public class DbHandler
{
	private SystemInventory inventory;
	private SystemAccounting accounting;
	/**
	 * Creates an instance of the inventory and accounting systems
	 */
	public DbHandler()
	{
		inventory = new SystemInventory();
		accounting = new SystemAccounting();
	}
	/**
	 * Checks if the items exists in the inventory.
	 * @param barcode Representing the identification the item.
	 * @return The item representing the given parameter.
	 */
	public boolean itemExists(int barcode)
	{
		return inventory.checkItem(barcode);
	}
	/**
	 * Receives the item if the items exists in the inventory.
	 * @param barcode Represents the item identifier.
	 * @return The item requested.
	 */
	public ItemDTO getScannedItem(int barcode)
	{
		if(itemExists(barcode))
			return inventory.getItem(barcode);
		else {
			return null;
		}
	}
	/**
	 * information about a particular sale to be stored in inventory and accounting
	 * @param listOfPruchasedItems the list of the scanned item during a particular sale.
	 */
	public void logSale(ArrayList<ItemDTO> listOfPruchasedItems)
	{
		inventory.register(listOfPruchasedItems);
		accounting.register(listOfPruchasedItems);
	}
}
