package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.collision.*;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.config.controller.game.DiskControllerConfig;

import java.util.Set;

public class Disk1Controller extends GameController {
    private final FieldCollisionDetector objectCollisionDetector;
    public Disk1Controller(Field field){
        super(field);
        objectCollisionDetector = new DiskObjectCollisionDetector(field);
    }

    private void moveDisk1() {
        Disk disk1 = getModel().getDisk1();
        Position diskPos = disk1.getPosition();
        Velocity diskVel = disk1.getVelocity();
        double newX = diskPos.getX() + diskVel.getX();
        double newY = diskPos.getY() + diskVel.getY();
        Position newPosition = new Position(newX, newY);
        CollisionAxis axis = objectCollisionDetector.collisionAxis(newPosition, disk1.getRadius());
        if  (axis != CollisionAxis.NONE) {
            CollisionResolver objectCollisionResolver = new DiskObjectCollisionResolver(disk1, axis);
            objectCollisionResolver.resolve();
        }
        double velX = disk1.getVelocity().getX();
        double velY = disk1.getVelocity().getY();
        disk1.setPosition(new Position(diskPos.getX() + velX, diskPos.getY() + velY));
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
       Disk disk1 = getModel().getDisk1();
       double velX = disk1.getVelocity().getX();
       double velY = disk1.getVelocity().getY();
       for (GUI.ACTION action : actionSet) {
           if (action == GUI.ACTION.KEY_W) velY -= DiskControllerConfig.ACCELERATION;
           if (action == GUI.ACTION.KEY_D) velX += DiskControllerConfig.ACCELERATION;
           if (action == GUI.ACTION.KEY_S) velY += DiskControllerConfig.ACCELERATION;
           if (action == GUI.ACTION.KEY_A) velX -= DiskControllerConfig.ACCELERATION;
       }
       velX *= DiskControllerConfig.ATTRITION_MULTIPLIER;
       velY *= DiskControllerConfig.ATTRITION_MULTIPLIER;

       double speed = Math.sqrt(velX * velX + velY * velY);
       if (speed > DiskControllerConfig.MAX_SPEED) {
            velX = (velX / speed) * DiskControllerConfig.MAX_SPEED;
            velY = (velY / speed) * DiskControllerConfig.MAX_SPEED;
       }

       disk1.setVelocity(new Velocity(velX, velY));
       moveDisk1();
    }
}
