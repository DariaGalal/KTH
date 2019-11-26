package se.kth.iv1350.saleScenario.dataBaseHandler;

public class DataBaseException extends RuntimeException
{
	/**
	 * An unchecked exception whenever there is a data base failure
	 * @param message
	 */
	public DataBaseException(String message)
	{
		super(message);
	}
}