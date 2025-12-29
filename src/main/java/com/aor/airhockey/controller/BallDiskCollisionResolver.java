package com.aor.airhockey.controller;

import com.aor.airhockey.config.controller.game.BallControllerConfig;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;

public class BallDiskCollisionResolver implements CollisionResolver {
    private Ball ball;
    private Disk disk;
    private CollisionInfo collisionInfo;
    private Position collisionPosition;

    public BallDiskCollisionResolver(Ball ball, Disk disk, CollisionInfo collisionInfo,  Position collisionPosition) {
        this.ball = ball;
        this.disk = disk;
        this.collisionInfo = collisionInfo;
        this.collisionPosition = collisionPosition;
    }

    @Override
    public void resolve(){
        double overlap = collisionInfo.getOverlap();
        double nx = collisionInfo.getNx();
        double ny = collisionInfo.getNy();
        Position ballPos = collisionPosition;
        Velocity ballVel = ball.getVelocity();
        Velocity diskVel = disk.getVelocity();

        if (overlap > 0) {
            //Separa a bola do disco
            double correctX = ballPos.getX() + nx * (overlap + 0.01);
            double correctY = ballPos.getY() + ny * (overlap + 0.01);
            ball.setPosition(new Position(correctX, correctY));
        }

        // calculamos a velocidade Relativa (Velocidade da Bola - Velocidade do Disco)
        double relVx = ballVel.getX() - diskVel.getX();
        double relVy = ballVel.getY() - diskVel.getY();

        // a velocidade ao longo da normal (Produto Escalar)
        double velocityAlongNormal = relVx * nx + relVy * ny;

        if (velocityAlongNormal > 0) {
            applyEscape();
            return; //foi tangente
        }

        // Vnew = Vold - 2(Vold . Normal) * Normal : formula fisica para calcular reflexão com impulso
        double j = -2 * velocityAlongNormal; //o j corresponde  ao impulso
        //a partir da formula, calculamos a nova velocidade
        double newVx = ballVel.getX() + j * nx;
        double newVy = ballVel.getY() + j * ny;

        // apllicamos um multiplicador de impacto á nova velocidade
        ball.setVelocity(new Velocity(newVx * BallControllerConfig.IMPACT_MULTIPLIER, newVy * BallControllerConfig.IMPACT_MULTIPLIER));

        applyEscape();

    }

    private void applyEscape() {
        Velocity newVel = ball.getVelocity();

        // Mexer a bola imediatamente para garantir que sai do disco
        double escapeX = newVel.getX() * 0.3;
        double escapeY = newVel.getY() * 0.3;

        Position currentPos = ball.getPosition();
        ball.setPosition(new Position(currentPos.getX() + escapeX, currentPos.getY() + escapeY));
    }
}
