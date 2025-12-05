package com.aor.airhockey.model.game.elements;

public class Disk extends Element {
    private double radius;
    private int playerNumber;

    public Disk(double x, double y, double r, int playerNumber) {
        super(x, y);
        this.radius = r;
        this.playerNumber = playerNumber;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
