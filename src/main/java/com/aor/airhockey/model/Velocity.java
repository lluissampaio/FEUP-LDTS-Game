package com.aor.airhockey.model;

import java.util.Objects;

public class Velocity {
    private double velX;
    private double velY;
    public Velocity(double x, double y) {
        this.velX = x;
        this.velY = y;
    }

    public double getX() {
        return this.velX;
    }
    public double getY() {
        return this.velY;
    }

    public void setVelX(double velX){this.velX=velX;}
    public void setVelY(double velY){this.velY=velY;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return velX == velocity.velX && velY == velocity.velY;
    }

    @Override
    public int hashCode() {return Objects.hash(velX, velY);}
}
