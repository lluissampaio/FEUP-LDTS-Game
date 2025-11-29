package com.aor.airhockey.controller;

import com.aor.airhockey.controller.game.Disk1Controller;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.elements.Goal;
import com.aor.airhockey.model.game.elements.Halfline;
import com.aor.airhockey.model.game.elements.Wall;
import com.aor.airhockey.model.game.field.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

class DiskControllerTest {
    private Disk1Controller controller;
    private Disk disk;
    private Field field;

    @BeforeEach
    void setUp() {
        field = new Field(10, 10);

        disk = new Disk(5, 5, 2);
        field.setDisk1(disk);

        field.setWalls(Arrays.asList());
        field.setHalfline(Arrays.asList());
        field.setGoals(Arrays.asList());

        controller = new Disk1Controller(field);
    }

    @Test
    void moveDiskRightEmpty(){
        controller.moveDisk1Right();
        Assertions.assertEquals(new Position(6, 5),  disk.getPosition());
    }

    @Test
    void moveDiskLeftEmpty(){
        controller.moveDisk1Left();
        Assertions.assertEquals(new Position(4, 5),  disk.getPosition());
    }

    @Test
    void moveDiskUpEmpty(){
        controller.moveDisk1Up();
        Assertions.assertEquals(new Position(5, 4),  disk.getPosition());
    }

    @Test
    void moveDiskDownEmpty(){
        controller.moveDisk1Down();
        Assertions.assertEquals(new Position(5, 6),  disk.getPosition());
    }

    @Test
    void moveDiskRightGoal(){
        field.setGoals(Arrays.asList(new Goal(6, 5)));
        controller.moveDisk1Right();
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskLeftHalfline(){
        field.setHalfline(Arrays.asList(new Halfline(4, 5)));
        controller.moveDisk1Left();
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskUpWall(){
        field.setWalls(Arrays.asList(new Wall(5, 4)));
        controller.moveDisk1Up();
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }

    @Test
    void moveDiskDownWall(){
        field.setWalls(Arrays.asList(new Wall(5, 6)));
        controller.moveDisk1Down();
        Assertions.assertEquals(new Position(5, 5),  disk.getPosition());
    }
}
