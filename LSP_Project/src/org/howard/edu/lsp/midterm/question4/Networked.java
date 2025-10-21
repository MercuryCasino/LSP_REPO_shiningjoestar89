package org.howard.edu.lsp.midterm.question4;

/**
 * An interface for devices that have networking capabilities.
 */
public interface Networked {
    /**
     * Connects the device to the network.
     */
    void connect();

    /**
     * Disconnects the device from the network.
     */
    void disconnect();

    /**
     * Checks if the device is currently connected.
     * @return true if connected, false otherwise.
     */
    boolean isConnected();
}
