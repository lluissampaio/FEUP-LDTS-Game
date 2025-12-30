package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.config.ConfigCalculator;
import com.aor.airhockey.config.model.game.elements.BallConfig;
import com.aor.airhockey.controller.collision.*;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.config.controller.game.BallControllerConfig;

import java.util.Set;

public class BallController extends GameController {
    private final FieldCollisionDetector wallCollisionDetector;
    private final CircleCollisionDetector diskCollisionDetector;
    public BallController(Field field){
        this(field, new BallWallCollisionDetector(field), new DiskBallCollisionDetector());
    }

    public BallController(Field field, FieldCollisionDetector wallCollisionDetector, CircleCollisionDetector diskCollisionDetector) {
        super(field);
        this.wallCollisionDetector = wallCollisionDetector;
        this.diskCollisionDetector = diskCollisionDetector;
    }

    public void moveBall() {

        Ball ball = getModel().getBall();
        Disk disk1 = getModel().getDisk1();
        Disk disk2 = getModel().getDisk2();

        double velX = ball.getVelocity().getX();
        double velY = ball.getVelocity().getY();


        double distance = Math.sqrt(velX * velX + velY * velY); //distância que a bola percorre neste frame
        double stepSize = 0.5; //defenir o tamanho do passo
        int steps = (int) Math.ceil(distance / stepSize); //quantos passos são precisos, arredondando para cima
        if (steps == 0) steps = 1; // Pelo menos 1 passo se estiver quase parado

        //calcular a velocidade do passo
        double stepX = velX / steps;
        double stepY = velY / steps;

        for (int i = 0; i < steps; i++) {
            // Recalcular a posição baseada no passo atual
            double nextX = ball.getPosition().getX() + stepX;
            double nextY = ball.getPosition().getY() + stepY;
            Position newPosition = new Position(nextX, nextY);
            CollisionAxis axis = wallCollisionDetector.collisionAxis(newPosition, ball.getRadius());

            if (axis != CollisionAxis.NONE) {
                CollisionResolver wallCollisionResolver = new BallWallCollisionResolver(ball, axis);
                wallCollisionResolver.resolve();

                if (axis == CollisionAxis.HORIZONTAL || axis == CollisionAxis.BOTH) {
                    //faz o mesmo que o código de cima mas é para controlar os steps
                    stepY = -stepY * Math.abs(BallControllerConfig.FRICTION_MULTIPLIER);
                    nextY = ball.getPosition().getY() + stepY; // Corrige a posição
                    newPosition = new Position(nextX, nextY);
                }
                if (axis == CollisionAxis.VERTICAL || axis == CollisionAxis.BOTH) {
                    stepX = -stepX * Math.abs(BallControllerConfig.FRICTION_MULTIPLIER);
                    nextX = ball.getPosition().getX() + stepX;
                    newPosition = new Position(nextX, nextY);
                }

            }

            Position gridPosition = new Position(Math.round(newPosition.getX()), Math.round(newPosition.getY()));
            CollisionInfo info1 = diskCollisionDetector.collisionInfo(disk1.getPosition(), disk1.getRadius(), gridPosition,  ball.getRadius());
            CollisionInfo info2 = diskCollisionDetector.collisionInfo(disk2.getPosition(), disk2.getRadius(), gridPosition,  ball.getRadius());

            if (info1 != null || info2 != null) {
                //aqui o código já sabe que houve colisão quando entra aqui

                Disk hitDisk;
                CollisionInfo hitInfo;
                if  (info1 == null) {
                    hitDisk = disk2;
                    hitInfo = info2;
                }
                else if (info2 == null) {
                    hitDisk = disk1;
                    hitInfo = info1;
                }
                else {
                    if (info1.getOverlap() >= info2.getOverlap()) {
                        hitDisk = disk1;
                        hitInfo = info1;
                    }
                    else {
                        hitDisk = disk2;
                        hitInfo = info2;
                    }
                }
                //como já sabemos que houve uma colisão, o que tiver menos distancia para a bola é o que colide

                CollisionResolver diskCollisionResolver = new BallDiskCollisionResolver(ball, hitDisk, hitInfo, gridPosition) ;
                diskCollisionResolver.resolve();


                // Como a velocidade mudou drasticamente, paramos este ciclo de passos
                // e deixamos o próximo frame tratar do novo movimento.
                // Isto evita que a bola entre "dentro" do disco no mesmo frame.
                return; //como muda muito, mais vale tratar noutro frame
            }


            ball.setPosition(newPosition); //a cada subpasso atualiza-se a posição da bola
        }
        double curVelX = ball.getVelocity().getX();
        double curVelY = ball.getVelocity().getY();
        double speed = Math.sqrt(curVelX * curVelX + curVelY * curVelY);
        if (speed > BallControllerConfig.MAX_SPEED) {
            double ratio = BallControllerConfig.MAX_SPEED / speed;
            ball.setVelocity(new Velocity(curVelX * ratio, curVelY * ratio));
        }//só para garantir que a velocidade da bola não fique muito grande
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        // check if we want to centralize the ball
        for (GUI.ACTION action : actionSet) {
            if (action == GUI.ACTION.KEY_R) {
                getModel().getBall().setPosition(new Position(ConfigCalculator.calculateCenterX(), ConfigCalculator.calculateCenterY()));
                getModel().getBall().setVelocity(new Velocity(BallConfig.DEFAULT_BALL_X_VELOCITY, BallConfig.DEFAULT_BALL_Y_VELOCITY));
            }
        }


        moveBall();

       if (getModel().getBall().getScoringPlayer() != 0) // Someone scored
       {
           getModel().getBall().setPosition(BallControllerConfig.MIDDLE_POSITION);
           if (getModel().getBall().getScoringPlayer()==1){ //par de if/else só para a bola ir para o campo de quem sofreu ponto
               getModel().getBall().setVelocity(new Velocity(BallConfig.DEFAULT_BALL_X_VELOCITY, BallConfig.DEFAULT_BALL_Y_VELOCITY));
           }
           else{
               getModel().getBall().setVelocity(new Velocity(-1.0*BallConfig.DEFAULT_BALL_X_VELOCITY, BallConfig.DEFAULT_BALL_Y_VELOCITY));
           }
           getModel().getBall().resetScoringPlayer();
       }
    }
}
