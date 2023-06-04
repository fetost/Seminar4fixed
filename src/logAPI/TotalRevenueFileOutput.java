package logAPI;

import se.kth.iv1350.model.SaleObserver;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TotalRevenueFileOutput implements SaleObserver {
    private PrintWriter writeToLog;
    private double revenue;

    public TotalRevenueFileOutput(){ 
        try{
            writeToLog = new PrintWriter(new FileWriter("Revenue.txt", true));
        } catch (IOException ioe) {
            System.out.println("ERROR: LOG could not be performed.");
            ioe.printStackTrace();
        }
    }

    public void addRevenue(double runningTotal){
        revenue += runningTotal;
        writeToLog.println("Total Revenue: " + revenue);
        writeToLog.flush();
    }
}
