package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the "Extract" phase of the ETL pipeline.
 * Its responsibility is to read data from a CSV file and convert it into a list of Product objects.
 */
public class CsvExtractor {
    /**
     * Reads a CSV file and extracts product data.
     *
     * @param filePath the relative path to the input CSV file
     * @return a list of Product objects representing the data rows
     * @throws IOException if the file cannot be found or read
     */
    public List<Product> extract(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // Skip header row
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    double price = Double.parseDouble(data[2]);
                    String category = data[3];
                    products.add(new Product(id, name, price, category));
                }
            }
        }
        return products;
    }
}