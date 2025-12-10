package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;

import java.io.IOException;
import java.util.Set;

public class BallController extends GameController {
    public BallController(Field field){ super(field); }

    public void moveBall() {

        Ball ball = getModel().getBall();
        Disk disk1 = getModel().getDisk1();
        Disk disk2 = getModel().getDisk2();

        double velX = ball.getVelX();
        double velY = ball.getVelY();

        double newX = ball.getPosition().getX() + velX;
        double newY = ball.getPosition().getY() + velY;

        Position newPosition = new Position(newX, newY);

        if (collisionWithHorWall(newPosition)) {
            ball.setVelY(-ball.getVelY()*0.95);//a multiplicação é só para simular atrito
            newY = ball.getPosition().getY() + ball.getVelY();
            newPosition = new Position(newX, newY);
        }
        else if (collisionWithVerWall(newPosition)) {
            ball.setVelX(-ball.getVelX()*0.95); //a multiplicação é só para simular atrito
            newX = ball.getPosition().getX() + ball.getVelX();
            newPosition = new Position(newX, newY);
        }
        Position gridPosition = new Position(Math.round(newPosition.getX()), Math.round(newPosition.getY())); //é preciso porque os discos só ocupam posições inteiras
        if (collisionWithDisk(gridPosition,disk1,disk2)) {

            Disk hitDisk = disk1.getPosition().equals(gridPosition) ? disk1 : disk2; // qual disco colidiu

            resolveDiskCollision(gridPosition,hitDisk,ball);

            // Recalcula posição final com a nova velocidade pós-choque
            newX = ball.getPosition().getX() + ball.getVelX();
            newY = ball.getPosition().getY() + ball.getVelY();
            newPosition = new Position(newX, newY);


            //para já repetimos aqui colisão com paredes para nao bugar a bola com a parede
            if (collisionWithHorWall(newPosition)) {
                ball.setVelY(-ball.getVelY()*0.95);//a multiplicação é só para simular atrito
                newY = ball.getPosition().getY() + ball.getVelY();
                newPosition = new Position(newX, newY);
            }
            else if (collisionWithVerWall(newPosition)) {
                ball.setVelX(-ball.getVelX()*0.95); //a multiplicação é só para simular atrito
                newX = ball.getPosition().getX() + ball.getVelX();
                newPosition = new Position(newX, newY);
            }
        }
        

        ball.setPosition(newPosition);
    }

    private boolean collisionWithHorWall(Position position) {
        Position roundDownPosition = new Position(Math.round(position.getX()), Math.round(position.getY() + 0.5));
        Position roundUpPosition = new Position(Math.round(position.getX()), Math.round(position.getY() - 0.5));
        if (getModel().isWall(roundUpPosition) || getModel().isWall(roundDownPosition)) {
            return true;
        }
        return false;
    }

    private boolean collisionWithVerWall(Position position) {
        Position roundLeftPosition = new Position(Math.round(position.getX() - 0.5), Math.round(position.getY()));
        Position roundRightPosition = new Position(Math.round(position.getX() + 0.5), Math.round(position.getY()));
        if (getModel().isWall(roundLeftPosition) || getModel().isWall(roundRightPosition)) {
            return true;
        }
        return false;
    }

    private boolean collisionWithDisk(Position position, Disk disk1, Disk disk2) {
        double collisionRadius = 1.5; //para já é assim mas futuramente tem de ser a soma entre os raios do disco e da bola

        double dist1 = getDistance(position, disk1.getPosition());
        double dist2 = getDistance(position, disk2.getPosition());

        return dist1 < collisionRadius || dist2 < collisionRadius;
    }

    private double getDistance(Position p1, Position p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)); //Teorema de pitagoras para saber a linha que liga a bola ao disco
    }

    private void resolveDiskCollision(Position ballPos, Disk disk, Ball ball) {
        Position diskPos = disk.getPosition();

        double dx = ballPos.getX() - diskPos.getX();
        double dy = ballPos.getY() - diskPos.getY();

        // Velocidade atual
        double vX = ball.getVelX();
        double vY = ball.getVelY();

        // colisão lateral
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) vX = Math.abs(vX);  // Bola à direita -> força velocidade positiva X
            else vX = -Math.abs(vX);        // Bola à esquerda -> força velocidade negativa X
        }
        // colisão superior/inferior
        else if (Math.abs(dy) > Math.abs(dx)) {
            if (dy > 0) vY = Math.abs(vY);  // Bola em baixo -> força velocidade positiva Y
            else vY = -Math.abs(vY);        // Bola em cima -> força velocidade negativa Y
        }
        // Colisão na diagonal
        else {
            if (dx > 0) vX = Math.abs(vX); else vX = -Math.abs(vX);
            if (dy > 0) vY = Math.abs(vY); else vY = -Math.abs(vY);
        }



        ball.setVelX(vX*1.05);//para já, só para aumeuntar a velocidade da bola
        ball.setVelY(vY*1.05);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        moveBall();
    }
}
