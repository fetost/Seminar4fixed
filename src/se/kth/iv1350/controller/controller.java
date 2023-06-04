package se.kth.iv1350.controller;

import java.util.*;
import logAPI.FileLogger;
import logAPI.TotalRevenueFileOutput;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

/**
 * Creates the controller class. 
 */
public class controller {
    private Sale sale; 
    public Payment payment;
    private Receipt receipt;
    private ExternalInventory extInv;
    private ExternalAccounting extAcc;
    public Printer printer; 
    private FileLogger logger;
    public double quantity; 
    private List<SaleObserver> saleObservers = new ArrayList<>(); 
    private TotalRevenueFileOutput totalRevenueFileOutput;
    
    /**
     * Creates an instance of controller
     * @param extInv Refers to the External Inventory Handler, to access the store's inventory.
     * @param extAcc Refers to the External Accounting Handler. to update the sale information.
     * @param printer To be able to print out the receipt at the end of the sale.
     */
    public controller(ExternalInventory extInv, ExternalAccounting extAcc, Printer printer){
        this.extInv = extInv; 
        this.extAcc = extAcc;
        this.printer = printer;  
        logger = new FileLogger();
        totalRevenueFileOutput = new TotalRevenueFileOutput();
        saleObservers.add(totalRevenueFileOutput); 
    }
    /**
     * Creates a new Sale object and returns a SaleDTO object representing the current sale state.
     * @return sale.getSaleInfo() Returns the SaleDTO initialized at the beggining. 
     */
    public SaleDTO startSale() {
        this.sale = new Sale();
        sale.addSaleObserver(saleObservers);
        return sale.getSaleInfo();
    }
    /**
     * Retrieves an item from ExternalInventory based on the given itemIdentifier and calculates the cost for the item.
     * @param itemIdentifier Requested item's ID number, used to compare and find items.
     * @param quantity Quantity of requested item for calculation. 
     * @return sale.getSaleInfo() returns the SaleDTO updated.
     * @throws DatabaseFailureException Exception for when an invalid item id is entered
     * @throws InvalidItemIdentifierException Simulated exception to represent a database could not be reached
     */
    public SaleDTO registerItem(int itemIdentifier, double quantity) throws DatabaseFailureException, InvalidItemIdentifierException{
        try{
        Item item = extInv.retrieveItem(itemIdentifier); 
            sale.calculateCost(item, quantity, sale); 
        }catch (DatabaseFailureException exception){
            logger.log(java.time.LocalDateTime.now() + " --- " + "ERROR: Database could not be reached");
            throw exception;
        }catch(InvalidItemIdentifierException exception){
            logger.log(java.time.LocalDateTime.now() + " --- " + "ERROR: Identifier " + itemIdentifier + " is not valid, please check inventory.");
            throw exception;
        }

    return sale.getSaleInfo();
    }

    /**Creates a Payment object and updates ExternalAccounting with the paid amount.
    Updates ExternalInventory with the items that were sold. It then creates a Receipt object and sends it to the Printer to be printed.
    *@param paidAmount Holds the amount paid by the customer to then get change. 
    */
    public void pay(double paidAmount){
        
        payment = new Payment(paidAmount, sale.getSaleInfo().getRunningTotal());
        extAcc.updateAccounting(paidAmount);
        extInv.updateInventory(sale);
    }

    public void getReceipt(){
        receipt = new Receipt(sale, payment); 
        printer.printReceipt(receipt);
    }

    /**
     * Designates a SaleObserver
     * @param saleObserver The observer 
     */
    public void addObserver(SaleObserver saleObserver){ 
        saleObservers.add(saleObserver);
    }

    public void finishSale(){
        sale.endSale();
    }
}
