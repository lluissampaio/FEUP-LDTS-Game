package com.aor.airhockey.controller;

import com.aor.airhockey.config.controller.game.BallControllerConfig;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Ball;

public class BallWallCollisionResolver implements CollisionResolver {
    private Ball ball;
    private final CollisionAxis collisionAxis;

    public BallWallCollisionResolver(Ball ball, CollisionAxis collisionAxis) {
        this.ball = ball;
        this.collisionAxis = collisionAxis;
    }

    @Override
    public void resolve(){
        Velocity velocity = ball.getVelocity();
        if (collisionAxis == CollisionAxis.HORIZONTAL || collisionAxis == CollisionAxis.BOTH) {
            velocity.setVelY(velocity.getY() * BallControllerConfig.FRICTION_MULTIPLIER);
        }
        if (collisionAxis == CollisionAxis.VERTICAL || collisionAxis == CollisionAxis.BOTH) {
            velocity.setVelX(velocity.getX() * BallControllerConfig.FRICTION_MULTIPLIER);
        }
        ball.setVelocity(velocity);
    }
}
