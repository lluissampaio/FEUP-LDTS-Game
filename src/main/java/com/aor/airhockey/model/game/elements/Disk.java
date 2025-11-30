package com.aor.airhockey.model.game.elements;

public class Disk extends Element {
    private double radius;

    public Disk(double x, double y, double r) {
        super(x, y);
        this.radius = r;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
