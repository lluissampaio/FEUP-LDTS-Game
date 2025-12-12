package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
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

        double velX = ball.getVelocity().getX();
        double velY = ball.getVelocity().getY();

        double newX = ball.getPosition().getX() + velX;
        double newY = ball.getPosition().getY() + velY;

        Position newPosition = new Position(newX, newY);

        Velocity ballVelocity = new Velocity(ball.getVelocity().getX(), ball.getVelocity().getY());

        if (collisionWithHorWall(newPosition,ball.getRadius())) {
            ballVelocity.setVelY(ballVelocity.getY()*-0.95);//a multiplicação é só para simular atrito
            ball.setVelocity(ballVelocity);
            newY = ball.getPosition().getY() + ball.getVelocity().getY();
            newPosition = new Position(newX, newY);
        }
        else if (collisionWithVerWall(newPosition,ball.getRadius())) {
            ballVelocity.setVelX(ballVelocity.getX()*-0.95);//a multiplicação é só para simular atrito
            ball.setVelocity(ballVelocity);
            newX = ball.getPosition().getX() + ball.getVelocity().getX();
            newPosition = new Position(newX, newY);
        }

        Position gridPosition = new Position(Math.round(newPosition.getX()), Math.round(newPosition.getY())); //é preciso porque os discos só ocupam posições inteiras
        if (collisionWithDisk(gridPosition, ball.getRadius(), disk1, disk2)) {
            //aqui o código já sabe que houve colisão quando entra aqui
            double distToDisk1 = getDistance(gridPosition, disk1.getPosition());
            double distToDisk2 = getDistance(gridPosition, disk2.getPosition());
            //cada um mede a distancia do centro do disco até ao centro da bola

            Disk hitDisk = distToDisk1 < distToDisk2 ? disk1 : disk2; // qual disco colidiu
            //como já sabemos que houve uma colisão, o que tiver menos distancia para a bola é o que colide

            resolveDiskCollision(gridPosition,hitDisk,ball);

            // Recalcula posição final com a nova velocidade pós-choque
            newX = ball.getPosition().getX() + ball.getVelocity().getX();
            newY = ball.getPosition().getY() + ball.getVelocity().getY();
            newPosition = new Position(newX, newY);

            ballVelocity = new Velocity(ball.getVelocity().getX(), ball.getVelocity().getY());

            //para já repetimos aqui colisão com paredes para nao bugar a bola com a parede
            if (collisionWithHorWall(newPosition,ball.getRadius())) {
                ballVelocity.setVelY(ballVelocity.getY()*-0.95);//a multiplicação é só para simular atrito
                ball.setVelocity(ballVelocity);
                newY = ball.getPosition().getY() + ball.getVelocity().getY();
                newPosition = new Position(newX, newY);
            }
            else if (collisionWithVerWall(newPosition,ball.getRadius())) {
                ballVelocity.setVelX(ballVelocity.getX()*-0.95);//a multiplicação é só para simular atrito
                ball.setVelocity(ballVelocity);
                newX = ball.getPosition().getX() + ball.getVelocity().getX();
                newPosition = new Position(newX, newY);
            }
        }

        ball.setPosition(newPosition);
    }

    private boolean collisionWithHorWall(Position position, double radius) {
        Position roundDownPosition = new Position(Math.round(position.getX()), Math.round(position.getY() + 0.5 + radius));
        Position roundUpPosition = new Position(Math.round(position.getX()), Math.round(position.getY() - 0.5 - radius));
        if (getModel().isWall(roundUpPosition) || getModel().isWall(roundDownPosition)) {
            return true;
        }
        return false;
    }

    private boolean collisionWithVerWall(Position position, double radius) {
        Position roundLeftPosition = new Position(Math.round(position.getX() - 0.5 - radius), Math.round(position.getY()));
        Position roundRightPosition = new Position(Math.round(position.getX() + 0.5 + radius), Math.round(position.getY()));
        if (getModel().isWall(roundLeftPosition) || getModel().isWall(roundRightPosition)) {
            return true;
        }
        return false;
    }

    private boolean collisionWithDisk(Position ballposition, double radius, Disk disk1, Disk disk2) {
        double collisionRadius = disk1.getRadius() + radius;

        double dist1 = getDistance(ballposition, disk1.getPosition());
        double dist2 = getDistance(ballposition, disk2.getPosition());

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
        double vX = ball.getVelocity().getX();
        double vY = ball.getVelocity().getY();

        // colisão lateral
        if (dy==0) {
            if (dx > 0) vX = Math.abs(vX);  // Bola à direita -> força velocidade positiva X
            else vX = -Math.abs(vX);        // Bola à esquerda -> força velocidade negativa X
        }
        // colisão superior/inferior
        else if (dx==0) {
            if (dy > 0) vY = Math.abs(vY);  // Bola em baixo -> força velocidade positiva Y
            else vY = -Math.abs(vY);        // Bola em cima -> força velocidade negativa Y
        }
        // Colisão na diagonal
        else {
            if (dx > 0) vX = Math.abs(vX); else vX = -Math.abs(vX);
            if (dy > 0) vY = Math.abs(vY); else vY = -Math.abs(vY);
        }


        Velocity newVelocity = new Velocity(vX*1.1,  vY*1.1); //para já, só para aumeuntar a velocidade da bola
        ball.setVelocity(newVelocity);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        moveBall();
    }
}
