package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.*;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.model.game.field.FieldBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

class FieldBuilderTest {

    static class Stub extends FieldBuilder {
        @Override public int getWidth() { return 0; }
        @Override public int getHeight() { return 0; }
        @Override public List<Wall> createWalls() { return null; }
        @Override public Disk createDisk(int DiskNumber) { return null; }
        @Override public Ball createBall() { return null; }
        @Override public List<Halfline> createHalfline() { return null; }
        @Override public List<Goal> createGoals() { return null; }
        @Override public List<Arc> createArcs() { return null; }
    }

    @Test
    void createField() {
        Stub stub = new Stub();
        Stub builderSpy = Mockito.spy(stub);

        List<Wall> walls = Collections.singletonList(Mockito.mock(Wall.class));
        List<Goal> goals = Collections.singletonList(Mockito.mock(Goal.class));
        List<Halfline> halflines = Collections.singletonList(Mockito.mock(Halfline.class));
        List<Arc> arcs = Collections.singletonList(Mockito.mock(Arc.class));
        Ball ball = Mockito.mock(Ball.class);
        Disk disk1 = Mockito.mock(Disk.class);
        Disk disk2 = Mockito.mock(Disk.class);

        Mockito.doReturn(100).when(builderSpy).getWidth();
        Mockito.doReturn(50).when(builderSpy).getHeight();
        Mockito.doReturn(walls).when(builderSpy).createWalls();
        Mockito.doReturn(goals).when(builderSpy).createGoals();
        Mockito.doReturn(halflines).when(builderSpy).createHalfline();
        Mockito.doReturn(arcs).when(builderSpy).createArcs();
        Mockito.doReturn(ball).when(builderSpy).createBall();
        Mockito.doReturn(disk1).when(builderSpy).createDisk(1);
        Mockito.doReturn(disk2).when(builderSpy).createDisk(2);

        Field field = builderSpy.createField();

        Assertions.assertNotNull(field);
        Assertions.assertEquals(100, field.getWidth());
        Assertions.assertEquals(50, field.getHeight());
        Assertions.assertEquals(walls, field.getWalls());
        Assertions.assertEquals(goals, field.getGoals());
        Assertions.assertEquals(halflines, field.getHalfline());
        Assertions.assertEquals(arcs, field.getArcs());
        Assertions.assertEquals(ball, field.getBall());
        Assertions.assertEquals(disk1, field.getDisk1());
        Assertions.assertEquals(disk2, field.getDisk2());
    }
}
