package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * The main orchestrator for the ETL (Extract-Transform-Load) pipeline.
 * This class coordinates the work of the Extractor, Transformer, and Loader classes.
 */
public class EtlPipeline {
    /**
     * The main entry point for the application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String inputFile = "LSP_Project/data/products.csv";
        String outputFile = "LSP_Project/data/transformed_products.csv";

        CsvExtractor extractor = new CsvExtractor();
        ProductTransformer transformer = new ProductTransformer();
        CsvLoader loader = new CsvLoader();

        try {
            // Extract
            List<Product> products = extractor.extract(inputFile);
            int rowsRead = products.size();

            // Transform
            transformer.transform(products);

            // Load
            loader.load(outputFile, products);

            // Print summary
            System.out.println("ETL Process Completed Successfully.");
            System.out.println("Rows read: " + rowsRead);
            System.out.println("Rows transformed: " + rowsRead);
            System.out.println("Output written to: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error: Could not find or read input file at " + inputFile);
            System.err.println("Please ensure the file exists and the program is run from the project root.");
        }
    }
}