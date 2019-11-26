package se.kth.iv1350.saleScenario.dataBaseHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemInventory
{
	private final HashMap<Integer, ItemDTO> listOfAvailableItems = new HashMap<Integer, ItemDTO>();
	/**
	 * Fills the inventory with immutable items available for purchase.
	 */
	public SystemInventory()
	{
		listOfAvailableItems.put(1000, new ItemDTO("Banana", 11));
		listOfAvailableItems.put(1001, new ItemDTO("Apple", 5));
		listOfAvailableItems.put(1002, new ItemDTO("Pork", 75));
		listOfAvailableItems.put(1003, new ItemDTO("Toilet Paper", 10));
		listOfAvailableItems.put(1004, new ItemDTO("Soda", 13));
	}
	/**
	 * Checks if the item exists in the inventory.
	 * @param barcode the identifier to find the item with.
	 * @return True if the item exists, false if the item does not exist.
	 * @throws DataBaseException When there is no communication with the data base.
	 * @throws ItemNotFoundException When an item with the following barcode does not match any of the barcodes in the inventory system.
	 */
	public boolean checkItem(int barcode) throws ItemNotFoundException, DataBaseException
	{
		if(barcode == 1337)
		{
			throw new DataBaseException("\n*To Developer*\nCOULD NOT REACH DATA BASE WHEN BARCODE 1337 WAS ENTERED\n");
		}
		
		if(!listOfAvailableItems.containsKey(barcode))
		{
			throw new ItemNotFoundException("\n*To Developer*\nITEM NOT FOUND WITH BARCODE: " + barcode + "\n");
		}
		return listOfAvailableItems.containsKey(barcode);
	}
	/**
	 * Sends the requested item.
	 * @param barcode the identifier to find the item with
	 * @return the item requested
	 */
	public ItemDTO getItem(int barcode)
	{
		return listOfAvailableItems.get(barcode);
	}
	/**
	 * Saves information about which items that has been sold and eventually send to a data base.
	 * @param itemsToBeRegistered
	 */
	public void register(ArrayList<ItemDTO> itemsToBeRegistered)
	{
		ArrayList<ItemDTO> saleData = new ArrayList<ItemDTO>();
		saleData = itemsToBeRegistered;
	}
}
