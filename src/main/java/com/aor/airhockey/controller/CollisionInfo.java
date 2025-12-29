package com.aor.airhockey.controller;

public class CollisionInfo {
    private double nx;
    private double ny;
    private double overlap;

    public CollisionInfo(double nx, double ny, double overlap) {
        this.nx = nx;
        this.ny = ny;
        this.overlap = overlap;
    }

    public double getNx() {
        return nx;
    }
    public double getNy() {
        return ny;
    }
    public double getOverlap() {
        return overlap;
    }
}
