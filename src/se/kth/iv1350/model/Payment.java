package se.kth.iv1350.model;

/**
  The Payment class represents a payment made by the customer during a sale.
  It contains information about the amount paid, the running total and the change returned.
 */
public class Payment {
    
    public double paidAmount; 
    private double runningTotal; 
    public double change; 

    /**
     Creates a new instance of Payment with the specified paid amount and running total.
      @param paidAmount The amount paid by the customer.
      @param runningTotal The running total of the sale.
     */
    public Payment(double paidAmount, double runningTotal){
        this.paidAmount = paidAmount; 
        this.runningTotal = runningTotal;
        change = paidAmount - runningTotal;
    }
    /**
      Gets the amount paid by the customer.
      @return The amount paid by the customer.
     */
    public double getPaidAmount(){
        return paidAmount;
    }

    /**
      Gets the running total of the sale.
      @return The running total of the sale.
     */
    public double getRunningTotal(){
        return runningTotal; 
    }

    /**
      Calculates and returns the change to be returned to the customer.
      @return The change to be returned to the customer.
     */
    public double getChange(){ 
         return change;  
    }
}
