package se.kth.iv1350.saleScenario.dataBaseHandler;

import java.util.ArrayList;
/**
 * Handles the communication with data bases
 *
 */
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
	 * @param barcode Representing the identification of the item.
	 * @return The item representing the given parameter.
	 * @throws ItemNotFoundException 
	 */
	public boolean itemExists(int barcode) throws ItemNotFoundException
	{
		return inventory.checkItem(barcode);
	}
	/**
	 * Receives the item if the items exists in the inventory.
	 * @param barcode Represents the item identifier.
	 * @return The item requested.
	 * @throws ItemNotFoundException If the searched item with the following barcode is not found.
	 */
	public ItemDTO getScannedItem(int barcode) throws ItemNotFoundException
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
