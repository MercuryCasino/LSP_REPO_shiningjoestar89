package org.howard.edu.lsp.assignment2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Created a Class to hold product data
class Product {
 int id;
 String name;
 double price;
 String category;
 String priceRange; 

 
 public Product(int id, String name, double price, String category) {
     this.id = id;
     this.name = name;
     this.price = price;
     this.category = category;
 }

 // Make it easier to write to csv
 public String toCsvString() {
     return String.join(",", 
         String.valueOf(id), 
         name, 
         String.format("%.2f", price), 
         category, 
         priceRange);
 }
}


public class ETLPipeline {
	
	public static void main(String[] args) {
		String inputFile= "LSP_Project/data/products.csv";
		String outputFile= "LSP_Project/data/transformed_products.csv";
		
		int rowsRead = 0;
		int rowsTransformed= 0;
		
		try {
			// Read Input File
            List<String[]> rawData = extract(inputFile);
            rowsRead = rawData.size();

            //Transform the file like Optimus 
            List<Product> transformedData = transform(rawData);
            rowsTransformed = transformedData.size();

            //LOAD
            load(outputFile, transformedData);

            // Print summary
            System.out.println("Rows read: " + rowsRead);
            System.out.println("Rows transformed: " + rowsTransformed);
            System.out.println("Output written to: " + outputFile);

		} catch (IOException e) {
			System.err.println("Error: Input file not found at "+ inputFile);
		}
		
		
	}
	public static List<String[]> extract(String filePath) throws IOException {
	    List<String[]> data = new ArrayList<>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	        String line;
	        reader.readLine(); // Skip header row

	        while ((line = reader.readLine()) != null) {
	            if (!line.trim().isEmpty()) {
	                data.add(line.split(","));
	            }
	        }
	    }
	    return data;
	}
	public static List<Product> transform(List<String[]> rawData) {
	    List<Product> products = new ArrayList<>();
	    
	    for (String[] row : rawData) {
	        // We Parse through the raw data
	        int id = Integer.parseInt(row[0]);
	        String originalName = row[1];
	        double originalPrice = Double.parseDouble(row[2]);
	        String originalCategory = row[3];

	        // Here we apply the Transformation(Optimus)
	        
	        // Uppercase Names
	        String transformedName = originalName.toUpperCase();

	        // We apply the discount here
	        double finalPrice = originalPrice;
	        if (originalCategory.equalsIgnoreCase("Electronics")) {
	            finalPrice = originalPrice * 0.90;
	            // Round the price
	            finalPrice = Math.round(finalPrice * 100.0) / 100.0;
	        }

	        // Change the category
	        String finalCategory = originalCategory;
	        if (originalCategory.equalsIgnoreCase("Electronics") && finalPrice > 500.00) {
	            finalCategory = "Premium Electronics";
	        }

	        // Create the new product with the transformed data
	        Product p = new Product(id, transformedName, finalPrice, finalCategory);
	        
	        // Logic for the price range
	        if (p.price <= 10.00) {
	            p.priceRange = "Low";
	        } else if (p.price <= 100.00) {
	            p.priceRange = "Medium";
	        } else if (p.price <= 500.00) {
	            p.priceRange = "High";
	        } else {
	            p.priceRange = "Premium";
	        }
	        
	        products.add(p);
	    }
	    return products;
	}
	public static void load(String filePath, List<Product> products) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	        // Rewrite to the Header row
	        writer.write("ProductID,Name,Price,Category,PriceRange");
	        writer.newLine();

	        // Rewrite the products
	        for (Product p : products) {
	            writer.write(p.toCsvString());
	            writer.newLine();
	        }
	    }
	}

}
