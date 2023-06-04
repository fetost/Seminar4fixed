package se.kth.iv1350.view;

import se.kth.iv1350.model.SaleObserver;

public class TotalRevenueView implements SaleObserver {
    private double revenue;

    public void addRevenue(double runningTotal){
        revenue += runningTotal;
        System.out.println("Total Revenue: " + revenue);
    }
}
