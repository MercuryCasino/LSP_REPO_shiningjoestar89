package org.howard.edu.lsp.assignment3;

/**
 * Represents a single product with its attributes.
 * This class encapsulates the data for a product record.
 */
public class Product {
    private int id;
    private String name;
    private double price;
    private String category;
    private String priceRange;

    /**
     * Constructs a new Product.
     *
     * @param id       the product ID
     * @param name     the product name
     * @param price    the product price
     * @param category the product category
     */
    public Product(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getPriceRange() { return priceRange; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    /**
     * Formats the product's data into a comma-separated string for CSV output.
     *
     * @return a CSV-formatted string representing the product
     */
    public String toCsvString() {
        return String.join(",",
                String.valueOf(id),
                name,
                String.format("%.2f", price),
                category,
                priceRange);
    }
}