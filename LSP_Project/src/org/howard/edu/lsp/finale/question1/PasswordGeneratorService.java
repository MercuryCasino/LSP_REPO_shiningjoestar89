package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;
import java.util.Random;

/**
 * A Singleton service for generating passwords using swappable strategies.
 */
public class PasswordGeneratorService {

    private static PasswordGeneratorService instance;
    private PasswordStrategy strategy;

    /*
     *
     * PART C — Required Design Pattern Documentation
     * 
     * 1. Identify the design pattern(s) you used:
     * - Singleton Pattern
     * - Strategy Pattern
     *
     * 2. Explain why these pattern(s) were appropriate for this problem:
     * - Singleton Pattern: Due to the requirement of "Provide a single shared access point"
     * and "Only one instance of the service may exist." The Singleton pattern is the best option as
     * only one instance is created and it provides a global point of access via getInstance().
     *
     * - Strategy Pattern: Due to the requirements of "Support multiple approaches,"
     * "Allow the caller to select the approach at run time," and "Make behavior swappable."
     * The Strategy pattern is the best option as it encapsulates the algorithms (basic, enhanced, letters) in separate
     * classes implementing a common interface. Futhermore, The Service (Context) holds a reference
     * to the interface, allowing the concrete algorithm to be changed dynamically via
     * setAlgorithm() without modifying the Service's core logic.
     */

    /**
     * Private constructor to prevent external instantiation.
     */
    private PasswordGeneratorService() {
    }

    /**
     * Returns the single instance of the PasswordGeneratorService.
     * @return The singleton instance.
     */
    public static synchronized PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * Sets the password generation algorithm at runtime.
     * @param name The name of the algorithm ("basic", "enhanced", "letters").
     */
    public void setAlgorithm(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Algorithm name cannot be null");
        }
        
        switch (name.toLowerCase()) {
            case "basic":
                this.strategy = new BasicPasswordStrategy();
                break;
            case "enhanced":
                this.strategy = new EnhancedPasswordStrategy();
                break;
            case "letters":
                this.strategy = new LettersPasswordStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + name);
        }
    }

    /**
     * Generates a password using the currently selected algorithm.
     * @param length The length of the password to generate.
     * @return The generated password.
     * @throws IllegalStateException if the algorithm has not been set.
     */
    public String generatePassword(int length) {
        if (strategy == null) {
            throw new IllegalStateException("No algorithm selected. Call setAlgorithm() first.");
        }
        return strategy.generate(length);
    }

    /**
     * Internal implementation of the "basic" strategy.
     * Uses java.util.Random and digits only (0-9).
     */
    private static class BasicPasswordStrategy implements PasswordStrategy {
        private static final String DIGITS = "0123456789";

        @Override
        public String generate(int length) {
            Random r = new Random();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int index = r.nextInt(DIGITS.length());
                sb.append(DIGITS.charAt(index));
            }
            return sb.toString();
        }
    }

    /**
     * Internal implementation of the "enhanced" strategy.
     * Uses java.security.SecureRandom and alphanumeric characters.
     */
    private static class EnhancedPasswordStrategy implements PasswordStrategy {
        private static final String ALLOWED = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        @Override
        public String generate(int length) {
            SecureRandom s = new SecureRandom();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int index = s.nextInt(ALLOWED.length());
                sb.append(ALLOWED.charAt(index));
            }
            return sb.toString();
        }
    }

    /**
     * Internal implementation of the "letters" strategy.
     * Output must contain letters only (A-Z, a-z).
     */
    private static class LettersPasswordStrategy implements PasswordStrategy {
        private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        @Override
        public String generate(int length) {
            Random r = new Random();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int index = r.nextInt(LETTERS.length());
                sb.append(LETTERS.charAt(index));
            }
            return sb.toString();
        }
    }
}