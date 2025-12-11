package com.aor.airhockey.model.game.elements;

public class Ball extends MovableElement {
    private double radius;

    public void setRadius(double r) { this.radius = r; }
    public double getRadius() { return this.radius; }

    public Ball(double x, double y, double r, double vx, double vy) {
        super(x, y, vx, vy);
        this.radius = r;
    }

    public Ball(double x, double y, double r) {
        this(x, y, r, 0.3, 0.2);
    }

}
