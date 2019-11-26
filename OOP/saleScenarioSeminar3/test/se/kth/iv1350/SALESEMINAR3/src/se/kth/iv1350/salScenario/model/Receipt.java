package se.kth.iv1350.salScenario.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.kth.iv1350.salScenario.dataBaseHandler.ItemDTO;
/**
 * Creates an instance representing the receipt with the information to be printed on the receipt.
 */
public class Receipt 
{
	public final ArrayList<ItemDTO> receipt;
	public final double totalWithTax;
	public final LocalDateTime saleTime;
	
	public Receipt(ArrayList<ItemDTO> infoToBeReceipt, double totalWithTax, LocalDateTime timeOfSale)
	{
		this.receipt = infoToBeReceipt;
		this.totalWithTax = totalWithTax;
		this.saleTime = timeOfSale;
	}
	
}
