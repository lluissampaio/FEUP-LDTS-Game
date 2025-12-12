package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.config.controller.game.DiskControllerConfig;

import java.io.IOException;
import java.util.Set;

public class Disk2Controller extends GameController {
    public Disk2Controller(Field field){ super(field); }

    private void moveDisk1() {
        Disk disk2 = getModel().getDisk2();
        Position diskPos = disk2.getPosition();
        Velocity diskVel = disk2.getVelocity();
        double newX = diskPos.getX() + diskVel.getX();
        double newY = diskPos.getY() + diskVel.getY();
        Position newPosition = new Position(newX, newY);
        if (collisionWithHorObject(newPosition, disk2.getRadius())){
            diskVel.setVelY(disk2.getVelocity().getY() * DiskControllerConfig.FRICTION_MULTIPLIER);
            disk2.setVelocity(diskVel);
        }
        if (collisionWithVerObject(newPosition, disk2.getRadius())){
            diskVel.setVelX(disk2.getVelocity().getX() * DiskControllerConfig.FRICTION_MULTIPLIER);
            disk2.setVelocity(diskVel);
        }
        double velX = disk2.getVelocity().getX();
        double velY = disk2.getVelocity().getY();
        disk2.setPosition(new Position(diskPos.getX() + velX, diskPos.getY() + velY));
    }

    private boolean collisionWithHorObject(Position position, double radius) {
        Position roundDownPosition = new Position(Math.round(position.getX()), Math.round(position.getY() + 0.5 + radius));
        Position roundUpPosition = new Position(Math.round(position.getX()), Math.round(position.getY() - 0.5 - radius));
        if (!getModel().isEmpty(roundUpPosition) || !getModel().isEmpty(roundDownPosition)) {
            return true;
        }
        return false;
    }

    private boolean collisionWithVerObject(Position position, double radius) {
        Position roundLeftPosition = new Position(Math.round(position.getX() - 0.5 - radius), Math.round(position.getY()));
        Position roundRightPosition = new Position(Math.round(position.getX() + 0.5 + radius), Math.round(position.getY()));
        if (!getModel().isEmpty(roundLeftPosition) || !getModel().isEmpty(roundRightPosition)) {
            return true;
        }
        return false;
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        Disk disk2 = getModel().getDisk2();
        double velX = disk2.getVelocity().getX();
        double velY = disk2.getVelocity().getY();
        for (GUI.ACTION action : actionSet) {
            if (action == GUI.ACTION.ARR_N) velY -= DiskControllerConfig.ACCELERATION;
            if (action == GUI.ACTION.ARR_E) velX += DiskControllerConfig.ACCELERATION;
            if (action == GUI.ACTION.ARR_S) velY += DiskControllerConfig.ACCELERATION;
            if (action == GUI.ACTION.ARR_W) velX -= DiskControllerConfig.ACCELERATION;
        }
        velX *= DiskControllerConfig.ATRITION_MULTIPLIER;
        velY *= DiskControllerConfig.ATRITION_MULTIPLIER;

        double speed = Math.sqrt(velX * velX + velY * velY);
        if (speed > DiskControllerConfig.MAX_SPEED) {
            velX = (velX / speed) * DiskControllerConfig.MAX_SPEED;
            velY = (velY / speed) * DiskControllerConfig.MAX_SPEED;
        }

        disk2.setVelocity(new Velocity(velX, velY));
        moveDisk1();
    }
}
