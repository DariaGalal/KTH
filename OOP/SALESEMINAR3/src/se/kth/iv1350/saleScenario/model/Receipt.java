package se.kth.iv1350.saleScenario.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.kth.iv1350.saleScenario.dataBaseHandler.ItemDTO;
/**
 * The receipt contains a list of the items scanned, the total amount including tax
 * and time of the sale.
 */
public class Receipt 
{
	public final ArrayList<ItemDTO> receipt;
	public final double totalWithTax;
	public final LocalDateTime saleTime;
	/**
	 * Creates an instance of the receipt with the information to fill the receipt with.
	 * @param infoToBeReceipt The list of items purchased
	 * @param totalWithTax The amount of money charged, including tax
	 * @param timeOfSale The time of sale.
	 */
	public Receipt(ArrayList<ItemDTO> infoToBeReceipt, double totalWithTax, LocalDateTime timeOfSale)
	{
		this.receipt = infoToBeReceipt;
		this.totalWithTax = totalWithTax;
		this.saleTime = timeOfSale;
	}
	
}
