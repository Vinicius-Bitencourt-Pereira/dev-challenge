package app;


import java.util.List;

import services.DataAnalyzerService;
import services.FileHandler;
import services.ReportGenerator;

public class DataAnalyzer {
    private static final String INPUT_DIRECTORY = System.getProperty("user.home") + "/data/in/";
    private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + "/data/out/";
    private static final String FILE_EXTENSION = ".dat";


    public static void main(String[] args) {
    	DataAnalyzer dataAnalyzer = new DataAnalyzer();
        dataAnalyzer.analyzeData();
    }

    public void analyzeData() {
        FileHandler fileHandler = new FileHandler(INPUT_DIRECTORY, OUTPUT_DIRECTORY, FILE_EXTENSION);
        List<String> inputFiles = fileHandler.getInputFiles();

        for (String inputFile : inputFiles) {
            List<String> lines = fileHandler.readLinesFromFile(inputFile);
            DataAnalyzerService analyzerService = new DataAnalyzerService();
            int numberOfCustomers = analyzerService.countCustomers(lines);
            int numberOfSellers = analyzerService.countSellers(lines);
            String mostExpensiveSaleId = analyzerService.getMostExpensiveSaleId(lines);
            String worstSalesman = analyzerService.getWorstSalesman(lines);
            String outputFileName = fileHandler.getOutputFileName(inputFile);
            ReportGenerator reportGenerator = new ReportGenerator(outputFileName);
            reportGenerator.writeReport(numberOfCustomers, numberOfSellers, mostExpensiveSaleId, worstSalesman);
        }
    }
}