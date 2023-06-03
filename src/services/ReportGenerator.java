package services;

import java.io.*;

public class ReportGenerator {
    private String outputFileName;

    public ReportGenerator(String outputFileName) {
        this.outputFileName = outputFileName;
    }

    public void writeReport(int numberOfCustomers, int numberOfSellers, String mostExpensiveSaleId, String worstSalesman) {
        try (PrintWriter writer = new PrintWriter(outputFileName)) {
            writer.println("Number of customers: " + numberOfCustomers);
            writer.println("Number of sellers: " + numberOfSellers);
            writer.println("ID of the most expensive sale: " + mostExpensiveSaleId);
            writer.println("Worst salesman: " + worstSalesman);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}