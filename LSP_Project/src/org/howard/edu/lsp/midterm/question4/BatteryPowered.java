package org.howard.edu.lsp.midterm.question4;

/**
 * An interface for devices that are powered by a battery.
 */
public interface BatteryPowered {
    /**
     * Gets the current battery percentage.
     * @return an integer between 0 and 100, inclusive.
     */
    int getBatteryPercent();

    /**
     * Sets the current battery percentage.
     * @param percent the new battery percentage.
     * @throws IllegalArgumentException if percent is not between 0 and 100.
     */
    void setBatteryPercent(int percent);
}
