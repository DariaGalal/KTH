package se.kth.iv1350.salScenario.view;

import se.kth.iv1350.salScenario.model.Receipt;
import se.kth.iv1350.salScenario.model.SaleInformation;
import se.kth.iv1350.saleScenario.controller.Controller;

/**
 * This is a placeholder for the view. It contains only hard coded calls to the controller.
 */
public class View 
{
    private Controller contr;
    
    /**
     * Constructs a new view, using the specified controller.
     * 
     * @param contr This controller will be used for all system operations.
     */
    public View(Controller contr) 
    {
        this.contr = contr;
    }
    /**
     * Creates a format for the information to be displayed in a particular way for each scanned item.
     * @param saleInfo Collects all the necessary information that needs to be displayed.
     * @return
     */   
    private String printItemDTO(SaleInformation saleInfo)
    {
    		StringBuilder itemInfo = new StringBuilder();
    	    itemInfo.append("Item: " + saleInfo.getItemName() + "\nPrice: $" + Double.toString(saleInfo.getItemPrice()) + "\n" + "Running Total: $" + saleInfo.getRunningTotal() + "\n");
    	    return itemInfo.toString();
    }
    /**
     * Creates a format for the receipt to be displayed in a particular way.
     * @param receiptInfo Collects all the items that has been scanned for this particular sale 
     * @return
     */
    private String printOutReceipt(Receipt receiptInfo)
    {
    	StringBuilder receiptDisplay = new StringBuilder();
    	for(int i = 0; i < receiptInfo.receipt.size(); i++) {
    		receiptDisplay.append("Item: " + receiptInfo.receipt.get(i).getName() + "\n" + "Price: $" + receiptInfo.receipt.get(i).getPrice() + "\n \n");
    	}
    	receiptDisplay.append("Total price: $" + Double.toString(receiptInfo.totalWithTax) + "\n \n" + "Date of sale: " + receiptInfo.saleTime);
    	return receiptDisplay.toString();
    }
    
    /**
     * Simulates a sample execution based on the basic flow containing calls to all system operations.
     */
    public void sampleExecution() 
    {
    	int[] barcodesToBeScanned = {1001, 1002, 1003, 1004};
        double cashAmount = 150;
    	
        System.out.println("-Starting a new sale-");
        contr.startSale();
        
        System.out.println("-Proceeding to scan items- \n");
        for(int i : barcodesToBeScanned)
        	System.out.println(printItemDTO(contr.scannedItem(i)));
        
        System.out.println("-Confirming Sale by showing the total price including tax- \n");
        System.out.println("Total with tax included: $" + Double.toString((contr.confirmSale())) + "\n");
        
        System.out.println("-Recieveing payment from customer and register sale to accounting and inventory- \n");
        System.out.println("The amount payed: $" + Double.toString(cashAmount) + "\n" + "The amount of change: $" + contr.registerPayment(cashAmount) + "\n");
        
        System.out.println("-Proceeding to print out receipt- \n");
        System.out.println("----------RECEIPT----------");
        System.out.println(printOutReceipt(contr.printReceipt()));
        System.out.println("-------END OF RECEIPT-------");
    }
}
