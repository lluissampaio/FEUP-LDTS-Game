package com.aor.airhockey.model.game.elements;

import com.aor.airhockey.model.Velocity;

public class MovableElement extends Element {
    private Velocity velocity;

    public MovableElement(double x, double y, double vx, double vy) {
        super(x, y);
        this.velocity = new Velocity(vx, vy);
    }

    public Velocity getVelocity() {
        return this.velocity;
    }
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
}
