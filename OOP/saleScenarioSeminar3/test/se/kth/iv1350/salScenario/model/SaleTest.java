package se.kth.iv1350.salScenario.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.salScenario.dataBaseHandler.ItemDTO;

public class SaleTest
{

	@BeforeEach
	void setUp() throws Exception 
	{
		Sale sale = new Sale();
	}
	
	@Test
	void testIfItemIsAdded() 
	{
		ItemDTO testItem = new ItemDTO("Banan", 22);
		ItemDTO testItem2 = new ItemDTO("Onion", 23);
		Sale sale = new Sale();
		sale.addScannedItem(testItem);
		sale.addScannedItem(testItem2);
		assertEquals(testItem.getName(), sale.dokumentedItems.get(0).getName());
		assertEquals(testItem2.getName(), sale.dokumentedItems.get(1).getName());
	}

	@Test
	void testIfAddItemReturnNull() 
	{
		ItemDTO testItem = null;
		Sale sale = new Sale();
		assertEquals(null, sale.addScannedItem(testItem));
	}
	
	@Test
	void testIfUpdateBalance()
	{
		Sale sale = new Sale();
		ItemDTO[] listOfTestItems = {new ItemDTO("Some item", 20), new ItemDTO("Another item", 11), new ItemDTO("yet another item", 12.99)};
		for(int i = 0; i < listOfTestItems.length; i++)
		{
			sale.addScannedItem(listOfTestItems[i]);
		}
		
		double totalWithTax = sale.getTotal();
		double changeMore = 50 - totalWithTax;
		double changeLess = 40 - totalWithTax;
		
		assertEquals(0, sale.updateBalance(totalWithTax), 0);
		assertEquals(changeMore, sale.updateBalance(50), 0);
		assertEquals(changeLess, sale.updateBalance(40), 0);
	}
	
	@Test
	void testReceipt()
	{
		Sale sale = new Sale();
		ItemDTO[] listOfTestItems = {new ItemDTO("Some item", 20), new ItemDTO("Another item", 11), new ItemDTO("yet another item", 12.99)};
		for(int i = 0; i < listOfTestItems.length; i++)
		{
			sale.addScannedItem(listOfTestItems[i]);
		}
		
		Receipt testReceipt = new Receipt(sale.dokumentedItems, sale.getTotal(), sale.saleTime);
		
		assertEquals(testReceipt.receipt.get(0).getName(), sale.createReceipt().receipt.get(0).getName());
		assertEquals(testReceipt.receipt.get(1).getName(), sale.createReceipt().receipt.get(1).getName());
		assertEquals(testReceipt.receipt.get(2).getName(), sale.createReceipt().receipt.get(2).getName());
	}

}
