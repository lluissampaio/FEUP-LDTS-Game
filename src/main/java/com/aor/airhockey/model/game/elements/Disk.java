package com.aor.airhockey.model.game.elements;

public class Disk extends MovableElement {
    private double radius;
    private int playerNumber;

    public Disk(double x, double y, double r, double vx, double vy, int playerNumber) {
        super(x, y, vx, vy);
        this.radius = r;
        this.playerNumber = playerNumber;
    }

    public Disk(double x, double y, double r, int playerNumber) {
        this(x, y, r, 0.0, 0.0,  playerNumber);
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
