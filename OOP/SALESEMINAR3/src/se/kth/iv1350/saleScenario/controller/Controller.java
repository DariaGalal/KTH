package se.kth.iv1350.saleScenario.controller;

import se.kth.iv1350.saleScenario.dataBaseHandler.DbHandler;
import se.kth.iv1350.saleScenario.dataBaseHandler.ItemNotFoundException;
import se.kth.iv1350.saleScenario.model.Receipt;
import se.kth.iv1350.saleScenario.model.Sale;
import se.kth.iv1350.saleScenario.model.SaleInformation;
import se.kth.iv1350.saleScenario.model.SaleObserver;

/**
 * This is the application's only controller. All calls to the model pass
 * through here.
 */
public class Controller {
	private DbHandler dbH;
	private Sale sale;

	public void addSaleObserver(SaleObserver observer)
	{
		if (sale != null) {
			sale.addsaleObserver(observer);
		}
	}
	
	/**
	 * Creates an empty instance of {@link Sale}, which will be used for all
	 * information regarding the sale that is now started.
	 */
	public void startSale() {
		sale = new Sale();
		dbH = new DbHandler();
	}

	/**
	 * Adds the scanned item to a list of items if the item exists in the system
	 * inventory.
	 * 
	 * @param barcode Used to identify an item.
	 * @return The item that has been scanned - name, price and the running total.
	 * @throws ItemNotFoundException If the searched item with the following barcode is not found.
	 */
	public SaleInformation scannedItem(int barcode) throws ItemNotFoundException {
		return sale.addScannedItem(dbH.getScannedItem(barcode));
	}

	/**
	 * Confirms the ongoing sale by calculating the total with tax.
	 * 
	 * @return Total with tax included
	 */
	public double confirmSale() {
		return sale.getTotal();
	}

	/**
	 * calculates the potential change depending on the amount of cash given by the
	 * customer and logs the purchase in the inventory- and accounting systems.
	 * 
	 * @param cashFromCustomer
	 *            The amount of cash received by the customer.
	 * @return The amount of change to be given to the customer.
	 */
	public double registerPayment(double cashFromCustomer) {
		dbH.logSale(sale.dokumentedItems);
		return sale.updateBalance(cashFromCustomer);
	}

	/**
	 * Creates a receipt based on a particular sale providing information about the
	 * sale to the customer.
	 * 
	 * @return Items purchased, their prices, total with tax and the date of sale.
	 */
	public Receipt printReceipt() {
		return sale.createReceipt();
	}
}