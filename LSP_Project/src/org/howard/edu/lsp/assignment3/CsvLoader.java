package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Handles the "Load" phase of the ETL pipeline.
 * Its responsibility is to write a list of transformed Product objects to a new CSV file.
 */
public class CsvLoader {
    /**
     * Writes a list of products to a specified CSV file.
     *
     * @param filePath the relative path to the output CSV file
     * @param products the list of transformed Product objects to write
     * @throws IOException if the file cannot be written to
     */
    public void load(String filePath, List<Product> products) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();
            for (Product p : products) {
                writer.write(p.toCsvString());
                writer.newLine();
            }
        }
    }
}