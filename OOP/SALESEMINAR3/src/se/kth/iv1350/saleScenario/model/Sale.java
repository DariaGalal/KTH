package se.kth.iv1350.saleScenario.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import se.kth.iv1350.saleScenario.dataBaseHandler.ItemDTO;

/**
 * Contains all the logic operations and variables regarding a particular sale.
 */
public class Sale
{
	private ArrayList<SaleObserver> saleObservers = new ArrayList<>();
	public final ArrayList<ItemDTO> dokumentedItems;
	private double runningTotal = 0;
	private double change = 0;
	private double totalWithTax = 0;
	public final double tax = 1.12;
    public final LocalDateTime saleTime;
    /**
     * Creates a new instance, and records the time it was created. This will be the time recorded
     * on the receipt for the sale. Creates a new instance of an array list which will be used to
     * store the scanned item.
     */
    public Sale()
    {
        saleTime = LocalDateTime.now();
        dokumentedItems = new ArrayList<ItemDTO>();
    }
    /**
     * Adds the scanned item to a list that records the items scanned
     * Creating a new instance together with the found scanned item, running total and total.
     *  
     * @param scannedItem The found scanned item
     * @return Information about the scanned item- name, price and running total.
     */
    public SaleInformation addScannedItem(ItemDTO scannedItem)
    {
    	if(scannedItem == null)
    		return null;
    	dokumentedItems.add(scannedItem);
    	runningTotal += scannedItem.getPrice();
    	SaleInformation saleInfo = new SaleInformation(scannedItem, runningTotal, totalWithTax);
    	return saleInfo;
    }
    
    private void notifyObservers()
    {
    	for(SaleObserver obs : saleObservers)
    	{
    		obs.revenue(getTotal());
    	}
    }
    
    public void addsaleObserver(SaleObserver obs)
    {
    	saleObservers.add(obs);
    }
    
    /**
     * Calculates the total with the tax included.
     * @return total with tax.
     */
    public double getTotal()
    {
    	totalWithTax = runningTotal*tax;
    	return totalWithTax;
    }
    /**
     * Calculates the amount of potential change to be given back to the customer.
     * @param cashFromCustomer the amount of cash given by the customer.
     * @return The amount of change to be given back to the customer.
     */
    public double updateBalance(double cashFromCustomer)
    {
    	change = cashFromCustomer - totalWithTax;
    	notifyObservers();
    	return change;
    }
    /**
     * Creates a new instance with all the items scanned during the sale, total with tax and the date of sale.
     * @return the information in the receipt - the scanned items, prices, total with tax and date of sale.
     */
    public Receipt createReceipt()
    {
    	return new Receipt(dokumentedItems, totalWithTax, saleTime);
    }
}
