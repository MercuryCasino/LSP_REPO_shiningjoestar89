package org.howard.edu.lsp.finale.question1;

/**
 * Interface defining the strategy for password generation.
 * This allows different algorithms to be implemented interchangeably.
 */
public interface PasswordStrategy {
    /**
     * Generates a password string of the specified length.
     * @param length The desired length of the password.
     * @return The generated password string.
     */
    String generate(int length);
}