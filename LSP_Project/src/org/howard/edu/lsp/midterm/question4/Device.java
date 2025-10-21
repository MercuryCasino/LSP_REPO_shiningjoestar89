package org.howard.edu.lsp.midterm.question4;

/**
 * An abstract base class representing a generic smart-campus device.
 * This class provides common properties like ID, location, and connection status.
 * It is not to be modified.
 */
public abstract class Device {
    private String id;
    private String location;
    private long lastHeartbeatEpochSeconds;
    private boolean connected;

    /**
     * Constructor for the Device class.
     * @param id The unique identifier for the device.
     * @param location The physical location of the device.
     * @throws IllegalArgumentException if id or location are null or empty.
     */
    public Device(String id, String location) {
        if (id == null || id.isEmpty() || location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Invalid id or location");
        }
        this.id = id;
        this.location = location;
        this.lastHeartbeatEpochSeconds = 0;
        this.connected = false;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public long getLastHeartbeatEpochSeconds() {
        return lastHeartbeatEpochSeconds;
    }

    public boolean isConnected() {
        return connected;
    }

    protected void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Updates the device's last heartbeat time to the current time.
     */
    public void heartbeat() {
        this.lastHeartbeatEpochSeconds = System.currentTimeMillis() / 1000;
    }

    /**
     * Abstract method to get the current status of the device.
     * Subclasses must provide a concrete implementation.
     * @return a string representing the device's status.
     */
    public abstract String getStatus();
}
