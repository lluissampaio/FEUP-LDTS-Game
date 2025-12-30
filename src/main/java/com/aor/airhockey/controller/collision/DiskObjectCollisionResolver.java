package com.aor.airhockey.controller.collision;

import com.aor.airhockey.config.controller.game.DiskControllerConfig;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Disk;

public class DiskObjectCollisionResolver implements CollisionResolver {
    private Disk disk;
    private final CollisionAxis collisionAxis;

    public DiskObjectCollisionResolver(Disk disk, CollisionAxis collisionAxis) {
        this.disk = disk;
        this.collisionAxis = collisionAxis;
    }

    @Override
    public void resolve(){
        Velocity velocity = disk.getVelocity();
        if (collisionAxis == CollisionAxis.HORIZONTAL || collisionAxis == CollisionAxis.BOTH) {
            velocity.setVelY(velocity.getY() * DiskControllerConfig.FRICTION_MULTIPLIER);
        }
        if (collisionAxis == CollisionAxis.VERTICAL || collisionAxis == CollisionAxis.BOTH) {
            velocity.setVelX(velocity.getX() * DiskControllerConfig.FRICTION_MULTIPLIER);
        }
        disk.setVelocity(velocity);
    }
}
