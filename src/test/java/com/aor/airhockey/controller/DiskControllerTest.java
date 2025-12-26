package com.aor.airhockey.controller;

import com.aor.airhockey.controller.game.Disk1Controller;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.Game;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Collections;

class DiskControllerTest {
    private Disk1Controller controller;
    private Disk disk;
    private Field field;
    private Game game;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(Field.class);
        game = Mockito.mock(Game.class);
        disk = new Disk(5, 5, 2, 1);
        Mockito.when(field.getDisk1()).thenReturn(disk);
        Mockito.when(field.isEmpty(Mockito.any())).thenReturn(true); // Simula campo vazio
        controller = new Disk1Controller(field);
        game = Mockito.mock(Game.class);
    }

    @Test
    void moveDiskRightEmpty() { //campo livre
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_D), 0);//simula movimento para a direita
        Assertions.assertTrue(disk.getPosition().getX() > 5.0);
    }

    @Test
    void moveDiskLeftEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_A), 0);
        Assertions.assertTrue(disk.getPosition().getX() < 5.0);
    }

    @Test
    void moveDiskUpEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_W), 0);
        Assertions.assertTrue(disk.getPosition().getY() < 5.0);
    }

    @Test
    void moveDiskDownEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_S), 0);
        Assertions.assertTrue(disk.getPosition().getY() > 5.0);
    }

    // O Field trata Wall, Goal e Halfline como !isEmpty, logo o controller deve reagir igual.
    @Test
    void collisionWithWallRight() {
        disk.setVelocity(new Velocity(2, 0)); //velocidade inicial para a direita
        Mockito.when(field.isEmpty(new Position(8, 5))).thenReturn(false);  // X = 5 (pos) + 0.5 + 2 (raio) = 7.5 -> Arredonda para 8. Na posição (8,5) a posição nao está vazia
        controller.step(game, Collections.emptySet(), 0);
        Assertions.assertTrue(disk.getVelocity().getX() <= 0); //ao bater na parede a velocidade inverte ou para
    }

    @Test
    void collisionWithGoalLeft() {
        disk.setVelocity(new Velocity(-2, 0));
        Mockito.when(field.isEmpty(new Position(3, 5))).thenReturn(false);
        Mockito.when(field.isEmpty(new Position(2, 5))).thenReturn(false);
        controller.step(game, Collections.emptySet(), 0);
        Assertions.assertTrue(disk.getVelocity().getX() >= 0);
    }

    @Test
    void collisionWithHalflineDown() {
        disk.setVelocity(new Velocity(0, 2));
        Mockito.when(field.isEmpty(new Position(5, 8))).thenReturn(false); // Halfline aqui
        controller.step(game, Collections.emptySet(), 0);
        Assertions.assertTrue(disk.getVelocity().getY() <= 0);
    }
}
