package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileHandler {
		
	private String inputDirectory;
	private String outputDirectory;
	private String fileExtension;

	public FileHandler(String inputDirectory, String outputDirectory, String fileExtension) {
		this.inputDirectory = inputDirectory;
		this.outputDirectory = outputDirectory;
		this.fileExtension = fileExtension;
	}

	public List<String> getInputFiles(){
		File directory = new File(inputDirectory);
		File[] files = directory.listFiles(file -> file.getName().endsWith(fileExtension));
		
		if (files != null) {
			List<String> fileNames = new ArrayList<>();
			for (File file : files) {
				fileNames.add(file.getName());
			}
			return fileNames;
		}
		else {
			return Collections.emptyList();
		}
	}
	
	public List<String> readLinesFromFile(String fileName){
		List<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(inputDirectory + fileName))){
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public String getOutputFileName(String inputFile) {
		String outputFileName = inputFile + fileExtension;
		return  outputDirectory + outputFileName;
	}
}
