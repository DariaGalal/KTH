package se.kth.iv1350.salScenario.startUp;

import se.kth.iv1350.salScenario.view.View;
import se.kth.iv1350.saleScenario.controller.Controller;

/**
 * @author Daria Galal.
 * Starts the entire application.
 */
public class Main 
{
    /** Starts the program by creating a controller and a view, as well as executing a series of 
     * method calls through the view that replicates a sale scenario.
     * @param args The program does not take any command line parameters. 
     */
    public static void main(String[] args) 
    {
        Controller contr = new Controller();
        View view = new View(contr);
        view.sampleExecution();
    }
}