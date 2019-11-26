package se.kth.iv1350.saleScenario.dataBaseHandler;

public class ItemNotFoundException extends Exception
{
	/**
	 * A checked exception when an item is not found.
	 * @param message The message that will be shown to the user/developer.
	 */
	public ItemNotFoundException(String message)
	{
		super(message);
	}
}
