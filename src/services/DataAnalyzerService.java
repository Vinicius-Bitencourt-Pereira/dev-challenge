package services;

import java.util.List;

public class DataAnalyzerService {
	public int countSellers(List<String> lines) {
		int numberOfSellers = 0;
		
		for (String line : lines) {
			String[] fields = line.split("ç");
			String recordType = fields[0];
			
			if (recordType.equals("001")) {
				numberOfSellers++;
			}
		}
		return numberOfSellers;
	}
	
	public int countCustomers(List<String> lines) {
		int numberOfCustomers = 0;
		
		for (String line : lines) {
			String[] fields = line.split("ç");
			String recordType = fields[0];
			
			if (recordType.equals("002")) {
				numberOfCustomers ++;
			}
		}
		return numberOfCustomers;
	}
	
	public String getMostExpensiveSaleId(List<String> lines) {
        double maxSaleValue = 0.0;
        String mostExpensiveSaleId = "";

        for (String line : lines) {
            String[] fields = line.split("ç");
            String recordType = fields[0];
            
            if (recordType.equals("003")) {
                String[] items = fields[2].replaceAll("[\\[\\]]", "").split(",");
                double totalSale = calculateTotalSale(items);

                if (totalSale > maxSaleValue) {
                    maxSaleValue = totalSale;
                    mostExpensiveSaleId = fields[1];
                }
            }
        }
        return mostExpensiveSaleId;
    }
	
	public String getWorstSalesman(List<String> lines) {
        double maxSaleValue = 0.0;
        String worstSalesman = "";

        for (String line : lines) {
            String[] fields = line.split("ç");
            String recordType = fields[0];

            if (recordType.equals("003")) {
                String[] items = fields[2].replaceAll("[\\[\\]]", "").split(",");
                double totalSale = calculateTotalSale(items);

                String salesmanName = fields[3];
                if (worstSalesman.isEmpty() || totalSale < maxSaleValue) {
                    worstSalesman = salesmanName;
                    maxSaleValue = totalSale;
                }
            }
        }
        return worstSalesman;
    }
	
	private double calculateTotalSale(String[] items) {
        double totalSale = 0.0;
        
        for (String item : items) {
            String[] itemFields = item.split("-");
            int quantity = Integer.parseInt(itemFields[1]);
            double price = Double.parseDouble(itemFields[2]);
            totalSale += quantity * price;
        }
        return totalSale;
    }
}
