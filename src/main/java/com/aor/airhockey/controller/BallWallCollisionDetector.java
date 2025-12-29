package com.aor.airhockey.controller;

import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;

public class BallWallCollisionDetector implements FieldCollisionDetector {
    private final Field field;

    public BallWallCollisionDetector(Field field) {
        this.field = field;
    }

    @Override
    public CollisionAxis collisionAxis(Position position, double radius) {
        if (checkHorizontalCollision(position, radius) && checkVerticalCollision(position, radius)) {
            return CollisionAxis.BOTH;
        }
        if  (checkHorizontalCollision(position, radius)) {
            return CollisionAxis.HORIZONTAL;
        }
        if (checkVerticalCollision(position, radius)) {
            return CollisionAxis.VERTICAL;
        }
        return CollisionAxis.NONE;
    }

    private boolean checkHorizontalCollision(Position position, double radius) {
        Position roundDownPosition = new Position(Math.round(position.getX()), Math.round(position.getY() + 0.5 + radius));
        Position roundUpPosition = new Position(Math.round(position.getX()), Math.round(position.getY() - 0.5 - radius));
        if (field.isWall(roundUpPosition) || field.isWall(roundDownPosition)) {
            return true;
        }
        return false;
    }

    private boolean checkVerticalCollision(Position position, double radius) {
        Position roundLeftPosition = new Position(Math.round(position.getX() - 0.5 - radius), Math.round(position.getY()));
        Position roundRightPosition = new Position(Math.round(position.getX() + 0.5 + radius), Math.round(position.getY()));
        if (field.isWall(roundLeftPosition) || field.isWall(roundRightPosition)) {
            return true;
        }
        return false;
    }
}
