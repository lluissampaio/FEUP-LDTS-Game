package com.aor.airhockey.controller;

import com.aor.airhockey.model.Position;

public class DiskBallCollisionDetector implements CircleCollisionDetector {

    @Override
    public CollisionInfo collisionInfo(Position position1, double radius1, Position position2, double radius2) {
        double distance = distance(position1, position2);
        double overlap = radius1 + radius2 -  distance;
        if (overlap <= 0) {
            return null;
        }
        double dx = position2.getX() - position1.getX();
        double dy = position2.getY() - position1.getY();
        double nx = dx / distance;
        double ny = dy / distance;
        return new CollisionInfo(nx, ny, overlap);
    }

    private double distance(Position position1, Position position2) {
        return Math.sqrt(Math.pow(position1.getX() - position2.getX(), 2) + Math.pow(position1.getY() - position2.getY(), 2)); //Teorema de pitagoras para saber a linha que liga a bola ao disco
    }
}
