package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.*;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.config.controller.game.DiskControllerConfig;

import java.util.Set;

public class Disk2Controller extends GameController {
    private final FieldCollisionDetector objectCollisionDetector;
    public Disk2Controller(Field field){
        super(field);
        objectCollisionDetector = new DiskObjectCollisionDetector(field);
    }

    private void moveDisk1() {
        Disk disk2 = getModel().getDisk2();
        Position diskPos = disk2.getPosition();
        Velocity diskVel = disk2.getVelocity();
        double newX = diskPos.getX() + diskVel.getX();
        double newY = diskPos.getY() + diskVel.getY();
        Position newPosition = new Position(newX, newY);
        CollisionAxis axis = objectCollisionDetector.collisionAxis(newPosition, disk2.getRadius());
        if (axis != CollisionAxis.NONE) {
            CollisionResolver objectCollisionResolver = new DiskObjectCollisionResolver(disk2, axis);
            objectCollisionResolver.resolve();
        }
        double velX = disk2.getVelocity().getX();
        double velY = disk2.getVelocity().getY();
        disk2.setPosition(new Position(diskPos.getX() + velX, diskPos.getY() + velY));
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        Disk disk2 = getModel().getDisk2();
        double velX = disk2.getVelocity().getX();
        double velY = disk2.getVelocity().getY();
        for (GUI.ACTION action : actionSet) {
            if (action == GUI.ACTION.ARR_UP) velY -= DiskControllerConfig.ACCELERATION;
            if (action == GUI.ACTION.ARR_RIGHT) velX += DiskControllerConfig.ACCELERATION;
            if (action == GUI.ACTION.ARR_DOWN) velY += DiskControllerConfig.ACCELERATION;
            if (action == GUI.ACTION.ARR_LEFT) velX -= DiskControllerConfig.ACCELERATION;
        }
        velX *= DiskControllerConfig.ATTRITION_MULTIPLIER;
        velY *= DiskControllerConfig.ATTRITION_MULTIPLIER;

        double speed = Math.sqrt(velX * velX + velY * velY);
        if (speed > DiskControllerConfig.MAX_SPEED) {
            velX = (velX / speed) * DiskControllerConfig.MAX_SPEED;
            velY = (velY / speed) * DiskControllerConfig.MAX_SPEED;
        }

        disk2.setVelocity(new Velocity(velX, velY));
        moveDisk1();
    }
}
