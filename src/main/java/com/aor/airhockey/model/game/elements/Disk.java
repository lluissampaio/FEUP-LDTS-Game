package com.aor.airhockey.model.game.elements;

public class Disk extends Element {
    private int radius;

    public Disk(int x, int y, int r) {
        super(x, y);
        this.radius = r;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
