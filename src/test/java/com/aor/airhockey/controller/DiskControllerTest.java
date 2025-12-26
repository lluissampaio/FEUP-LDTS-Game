package com.aor.airhockey.controller;

import com.aor.airhockey.controller.game.Disk1Controller;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.elements.Goal;
import com.aor.airhockey.model.game.elements.Halfline;
import com.aor.airhockey.model.game.elements.Wall;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.Game;
import org.mockito.Mockito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

class DiskControllerTest {
    private Disk1Controller controller;
    private Disk disk;
    private Field field;
    private Game game;

    @BeforeEach
    void setUp() {
        field = Mockito.mock(Field.class);
        disk = new Disk(5, 5, 2, 1);
        Mockito.when(field.getDisk1()).thenReturn(disk);
        Mockito.when(field.isEmpty(Mockito.any())).thenReturn(true); // Simula campo vazio
        controller = new Disk1Controller(field);
        game = Mockito.mock(Game.class);
    }

    @Test
    void moveDiskRightEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_D), 0);
        Assertions.assertEquals(new Position(6, 5),  disk.getPosition());
    }

    @Test
    void moveDiskLeftEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_A), 0);
        Assertions.assertEquals(new Position(4, 5),  disk.getPosition());
    }

    @Test
    void moveDiskUpEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_W), 0);
        Assertions.assertEquals(new Position(5, 4),  disk.getPosition());
    }

    @Test
    void moveDiskDownEmpty(){
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_S), 0);
        Assertions.assertEquals(new Position(5, 6),  disk.getPosition());
    }

    @Test
    void moveDiskRightGoal(){
        Mockito.when(field.isEmpty(new Position(6, 5))).thenReturn(false); // Goal em (6,5)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_D), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskLeftGoal(){
        Mockito.when(field.isEmpty(new Position(4, 5))).thenReturn(false); // Goal em (4,5)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_A), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskRightHalfline(){
        Mockito.when(field.isEmpty(new Position(6, 5))).thenReturn(false); // Halfline em (5,6)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_D), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskLeftHalfline(){
        Mockito.when(field.isEmpty(new Position(4, 5))).thenReturn(false); // Halfline em (5,4)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_A), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskUpWall(){
        Mockito.when(field.isEmpty(new Position(5, 4))).thenReturn(false); // parede em (5,4)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_W), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskDownWall(){
        Mockito.when(field.isEmpty(new Position(5, 6))).thenReturn(false); // parede em (5,6)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_S), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskLeftWall(){
        Mockito.when(field.isEmpty(new Position(4, 5))).thenReturn(false); // parede em (4,5)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_A), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskRightWall(){
        Mockito.when(field.isEmpty(new Position(6, 5))).thenReturn(false); // parede em (6,5)
        controller.step(game, Collections.singleton(GUI.ACTION.KEY_D), 0);
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

}
