package com.aor.airhockey.model.game.elements;

import com.aor.airhockey.config.model.game.elements.BallConfig;

public class Ball extends MovableElement {
    private double radius;
    private int scoringPlayer = 0; // 0 -> no goal, 1 -> player1 goal, 2 -> player2 goal

    public void setRadius(double r) { this.radius = r; }
    public double getRadius() { return this.radius; }

    public void player2Scored() { this.scoringPlayer = 2; }
    public void player1Scored() { this.scoringPlayer = 1; }
    public void resetScoringPlayer() { this.scoringPlayer = 0; }

    public int getScoringPlayer() { return this.scoringPlayer; }

    public Ball(double x, double y, double r, double vx, double vy) {
        super(x, y, vx, vy);
        this.radius = r;
    }

    public Ball(double x, double y, double r) {
        this(x, y, r, BallConfig.DEFAULT_BALL_X_VELOCITY, BallConfig.DEFAULT_BALL_Y_VELOCITY);
    }

}
