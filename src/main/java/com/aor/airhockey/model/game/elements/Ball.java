package com.aor.airhockey.model.game.elements;

public class Ball extends Element {
    private int radius;

    public void setRadius(int r) { this.radius = r; }
    public int getRadius() { return this.radius; }

    public Ball(int x, int y, int r) {
        super(x, y);
        this.radius = r;
    }
}
