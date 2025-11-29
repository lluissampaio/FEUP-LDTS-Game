package com.aor.airhockey.model.game.elements;

public class Ball extends Element {
    private double radius;
    private double velX;
    private double velY;

    public void setRadius(int r) { this.radius = r; }
    public double getRadius() { return this.radius; }

    public Ball(double x, double y, double r, double vx, double vy) {
        super(x, y);
        this.radius = r;
        this.velX = vx;
        this.velY = vy;
    }

    public Ball(double x, double y, double r) {
        this(x, y, r, 0.3, 0.2);
    }

    public double getVelX() { return this.velX; }
    public double getVelY() { return this.velY; }
    public void setVelX(double v) { this.velX = v; }
    public void setVelY(double v) { this.velY = v; }


}
