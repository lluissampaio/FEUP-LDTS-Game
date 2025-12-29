package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.*;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;


class FieldTest {
    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field(100, 50);
    }

    @Test
    void constructorAndDimensions() {
        Assertions.assertEquals(100, field.getWidth());
        Assertions.assertEquals(50, field.getHeight());

        Assertions.assertNotNull(field.getScore());//para verificar se foram criados
        Assertions.assertNotNull(field.getTimer());
    }

    @Test
    void settersAndGetters() {
        Ball ball = Mockito.mock(Ball.class);
        field.setBall(ball);
        Assertions.assertEquals(ball, field.getBall());

        Disk disk = Mockito.mock(Disk.class);
        field.setDisk1(disk);
        Assertions.assertEquals(disk, field.getDisk1());
    }

    @Test
    void isEmpty() {
        Wall wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getPosition()).thenReturn(new Position(10, 10));//criar parede em 10,10
        field.setWalls(Collections.singletonList(wall));

        Halfline halfline = Mockito.mock(Halfline.class);
        Mockito.when(halfline.getPosition()).thenReturn(new Position(20, 20));//criar halfline 20,20
        field.setHalfline(Collections.singletonList(halfline));

        Goal goal = Mockito.mock(Goal.class);
        Mockito.when(goal.getPosition()).thenReturn(new Position(30, 30));//criar goal 30,30
        field.setGoals(Collections.singletonList(goal));

        // A. Posição Ocupada por Parede
        Assertions.assertFalse(field.isEmpty(new Position(10, 10)), "Deve retornar False se houver uma parede");

        // B. Posição Ocupada por Halfline
        Assertions.assertFalse(field.isEmpty(new Position(20, 20)), "Deve retornar False se houver uma halfline");

        // C. Posição Ocupada por Goal
        Assertions.assertFalse(field.isEmpty(new Position(30, 30)), "Deve retornar False se houver um golo");

        // D. Posição Vazia (Livre)
        Assertions.assertTrue(field.isEmpty(new Position(5, 5)), "Deve retornar True se estiver vazio");
    }

    @Test
    void isWall() {
        Wall wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getPosition()).thenReturn(new Position(5, 5));
        field.setWalls(Collections.singletonList(wall));

        Assertions.assertTrue(field.isWall(new Position(5, 5)));
        Assertions.assertFalse(field.isWall(new Position(6, 6)));
    }

    @Test
    void getWinnerNormalTime() {
        Timer timer = Mockito.mock(Timer.class);
        Mockito.when(timer.isOvertime()).thenReturn(false);//dizemos que é tempo normal
        field.setTimer(timer);
        Score score = Mockito.mock(Score.class);
        field.setScore(score);

        // Caso A: Ninguém ganhou ainda (ex: 2-3)
        Mockito.when(score.getPlayer1Points()).thenReturn(2);
        Mockito.when(score.getPlayer2Points()).thenReturn(3);
        Assertions.assertEquals(0, field.getWinner(), "Ninguém deve ganhar com menos de 4 pontos");

        // Caso B: Player 1 ganha (4-1)
        Mockito.when(score.getPlayer1Points()).thenReturn(4);
        Mockito.when(score.getPlayer2Points()).thenReturn(1);
        Assertions.assertEquals(1, field.getWinner(), "Player 1 deve ganhar ao chegar a 4");

        // Caso C: Player 2 ganha (3-5)
        Mockito.when(score.getPlayer1Points()).thenReturn(3);
        Mockito.when(score.getPlayer2Points()).thenReturn(5);
        Assertions.assertEquals(2, field.getWinner(), "Player 2 deve ganhar ao passar de 4");
    }

    @Test
    void getWinnerOvertime() {
        Timer timer = Mockito.mock(Timer.class);
        Mockito.when(timer.isOvertime()).thenReturn(true);//dizemos que é overtime
        field.setTimer(timer);

        Score score = Mockito.mock(Score.class);
        field.setScore(score);

        // Caso A: Player 1 tem mais pontos (2-1) -> Ganha Player 1
        Mockito.when(score.getPlayer1Points()).thenReturn(2);
        Mockito.when(score.getPlayer2Points()).thenReturn(1);
        Assertions.assertEquals(1, field.getWinner());

        // Caso B: Player 2 tem mais pontos (2-3) -> Ganha Player 2
        Mockito.when(score.getPlayer1Points()).thenReturn(2);
        Mockito.when(score.getPlayer2Points()).thenReturn(3);
        Assertions.assertEquals(2, field.getWinner());

        // Caso C: Empate (2-2) -> Ninguém ganha (0)
        Mockito.when(score.getPlayer1Points()).thenReturn(2);
        Mockito.when(score.getPlayer2Points()).thenReturn(2);
        Assertions.assertEquals(0, field.getWinner());
    }
}