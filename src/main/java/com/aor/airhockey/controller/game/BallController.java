package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.config.controller.game.BallControllerConfig;

import java.util.Set;

public class BallController extends GameController {
    public BallController(Field field){ super(field); }

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

            // Horizontal
            if (collisionWithHorWall(newPosition, ball.getRadius())) {
                Velocity v = ball.getVelocity();
                v.setVelY(v.getY() * BallControllerConfig.FRICTION_MULTIPLIER);
                ball.setVelocity(v); //mudar a velocidade oficial da bola

                //faz o mesmo que o código de cima mas é para controlar os steps
                stepY = -stepY * Math.abs(BallControllerConfig.FRICTION_MULTIPLIER);
                nextY = ball.getPosition().getY() + stepY; // Corrige a posição
                newPosition = new Position(nextX, nextY);
            }

            // Vertical
            if (collisionWithVerWall(newPosition, ball.getRadius())) {
                Velocity v = ball.getVelocity();
                v.setVelX(v.getX() * BallControllerConfig.FRICTION_MULTIPLIER);
                ball.setVelocity(v); //mudar a velocidade oficial da bola

                //faz o mesmo que o código de cima mas é para controlar os steps
                stepX = -stepX * Math.abs(BallControllerConfig.FRICTION_MULTIPLIER);
                nextX = ball.getPosition().getX() + stepX;
                newPosition = new Position(nextX, nextY);
            }

            Position gridPosition = new Position(Math.round(newPosition.getX()), Math.round(newPosition.getY()));
            if (collisionWithDisk(gridPosition, ball.getRadius(), disk1, disk2)) {
                //aqui o código já sabe que houve colisão quando entra aqui
                double distToDisk1 = getDistance(gridPosition, disk1.getPosition());
                double distToDisk2 = getDistance(gridPosition, disk2.getPosition());
                //cada um mede a distancia do centro do disco até ao centro da bola

                Disk hitDisk = distToDisk1 < distToDisk2 ? disk1 : disk2;
                //como já sabemos que houve uma colisão, o que tiver menos distancia para a bola é o que colide

                resolveDiskCollision(gridPosition, hitDisk, ball);

                // Como a velocidade mudou drasticamente, paramos este ciclo de passos
                // e deixamos o próximo frame tratar do novo movimento.
                // Isto evita que a bola entre "dentro" do disco no mesmo frame.
                ball.setPosition(newPosition);
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
        Velocity diskVel = disk.getVelocity();
        Velocity ballVel = ball.getVelocity();

        //vetor Normal (do centro do disco para a bola)
        double dx = ballPos.getX() - diskPos.getX();
        double dy = ballPos.getY() - diskPos.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance == 0) return; // Evitar erros matemáticos

        //vetor Normal normalizado, para termos a direção da colisão
        double nx = dx / distance;
        double ny = dy / distance;

        // calculamos a velocidade Relativa (Velocidade da Bola - Velocidade do Disco)
        double relVx = ballVel.getX() - diskVel.getX();
        double relVy = ballVel.getY() - diskVel.getY();

        // a velocidade ao longo da normal (Produto Escalar)
        double velocityAlongNormal = relVx * nx + relVy * ny;

        if (velocityAlongNormal > 0) return; //foi tangente

        // Vnew = Vold - 2(Vold . Normal) * Normal : formulla fisica para calcular reflexão com impulso
        double j = -2 * velocityAlongNormal; //o j corresponde  ao impulso
        //a partir da formula, calculamos a nova velocidade
        double newVx = ballVel.getX() + j * nx;
        double newVy = ballVel.getY() + j * ny;

        // apllicamos um multiplicador de impacto á nova velocidade
        ball.setVelocity(new Velocity(newVx * BallControllerConfig.IMPACT_MULTIPLIER, newVy * BallControllerConfig.IMPACT_MULTIPLIER));
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) {
        moveBall();
    }
}
