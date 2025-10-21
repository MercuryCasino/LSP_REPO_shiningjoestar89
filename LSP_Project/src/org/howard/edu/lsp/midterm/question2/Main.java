package org.howard.edu.lsp.midterm.question2;

/**
 * Main class to demonstrate the AreaCalculator's overloaded methods.
 */
public class Main {

    /*
     * Regarding the use of overloading vs. different method names:
     * Method overloading is used here because the conceptual action—calculating an area—is the same
     * for all shapes. Using a single, intuitive method name ("area") provides a cleaner and more
     * readable API, as the caller only needs to remember one name and the compiler resolves the
     * correct version based on the arguments provided. This is preferable to cluttering the
     * class with many similar names like "calculateCircleArea", "calculateRectangleArea", etc.
     */

    public static void main(String[] args) {
        // Demonstrate successful calls to each overloaded method
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));
        System.out.println(); // Add a blank line for separation

        // Demonstrate exception handling for invalid input
        try {
            System.out.println("Attempting to calculate area with an invalid dimension...");
            AreaCalculator.area(-5); // This will throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println("Caught an exception as expected: " + e.getMessage());
        }
    }
}