package se.kth.iv1350.dataBaseHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.salScenario.dataBaseHandler.ItemDTO;
import se.kth.iv1350.salScenario.dataBaseHandler.SystemInventory;


class SystemInventoryTest
{

	@BeforeEach
	void setUp() throws Exception
	{
		SystemInventory inventory = new SystemInventory();
	}

	@Test
	void testCheckItem()
	{
		SystemInventory inventory = new SystemInventory();
		boolean itemExists = true;
		boolean itemDoNotExist = false;
		assertEquals(itemExists, inventory.checkItem(1001));
		assertEquals(itemDoNotExist, inventory.checkItem(9999));
	}
	
	@Test
	void testGetItem()
	{
		SystemInventory inventory = new SystemInventory();
		ItemDTO testItem = new ItemDTO("Banana", 11);
		assertEquals(testItem.getName(), inventory.getItem(1000).getName());
		assertEquals(testItem.getPrice(), inventory.getItem(1000).getPrice());
	}

}
