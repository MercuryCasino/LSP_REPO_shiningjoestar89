package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Handles the "Transform" phase of the ETL pipeline.
 * This class applies a series of business rules to a list of Product objects.
 */
public class ProductTransformer {
    /**
     * Applies all required transformations to a list of products.
     *
     * @param products the list of Product objects to transform
     */
    public void transform(List<Product> products) {
        for (Product p : products) {
            String originalCategory = p.getCategory();

            // 1. Uppercase name
            p.setName(p.getName().toUpperCase());

            // 2. Apply discount if "Electronics"
            if (originalCategory.equalsIgnoreCase("Electronics")) {
                double discountedPrice = p.getPrice() * 0.90;
                // Round to two decimals
                p.setPrice(Math.round(discountedPrice * 100.0) / 100.0);
            }

            // 3. Recategorize if "Electronics" and price > $500
            if (originalCategory.equalsIgnoreCase("Electronics") && p.getPrice() > 500.00) {
                p.setCategory("Premium Electronics");
            }

            // 4. Compute PriceRange
            double finalPrice = p.getPrice();
            if (finalPrice <= 10.00) {
                p.setPriceRange("Low");
            } else if (finalPrice <= 100.00) {
                p.setPriceRange("Medium");
            } else if (finalPrice <= 500.00) {
                p.setPriceRange("High");
            } else {
                p.setPriceRange("Premium");
            }
        }
    }
}