package com.aor.airhockey.model.game.elements;

import com.aor.airhockey.config.model.game.elements.BallConfig;

public class Ball extends MovableElement {
    private double radius;

    public void setRadius(double r) { this.radius = r; }
    public double getRadius() { return this.radius; }

    public Ball(double x, double y, double r, double vx, double vy) {
        super(x, y, vx, vy);
        this.radius = r;
    }

    public Ball(double x, double y, double r) {
        this(x, y, r, BallConfig.DEFAULT_BALL_X_VELOCITY, BallConfig.DEFAULT_BALL_Y_VELOCITY);
    }

}
