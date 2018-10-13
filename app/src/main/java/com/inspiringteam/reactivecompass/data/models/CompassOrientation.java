package com.inspiringteam.reactivecompass.data.models;

public final class CompassOrientation {
    private float destinationDirection;

    private float lastDestinationDirection;

    private float polesDirection;

    private float lastPolesDirection;

    public CompassOrientation() {
    }

    public CompassOrientation(float destinationDirection, float lastDestinationDirection, float polesDirection, float lastPolesDirection) {

        this.destinationDirection = destinationDirection;
        this.lastDestinationDirection = lastDestinationDirection;
        this.polesDirection = polesDirection;
        this.lastPolesDirection = lastPolesDirection;
    }

    public float getDestinationDirection() {
        return destinationDirection;
    }

    public void setDestinationDirection(float destinationDirection) {
        this.destinationDirection = destinationDirection;
    }

    public float getPolesDirection() {
        return polesDirection;
    }

    public void setPolesDirection(float polesDirection) {
        this.polesDirection = polesDirection;
    }

    public float getLastDestinationDirection() {
        return lastDestinationDirection;
    }

    public void setLastDestinationDirection(float lastDestinationDirection) {
        this.lastDestinationDirection = lastDestinationDirection;
    }

    public float getLastPolesDirection() {
        return lastPolesDirection;
    }

    public void setLastPolesDirection(float lastPolesDirection) {
        this.lastPolesDirection = lastPolesDirection;
    }
}
