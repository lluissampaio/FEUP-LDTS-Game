package com.aor.airhockey.model.game.field;

import com.aor.airhockey.model.game.elements.*;

import java.util.List;

public abstract class FieldBuilder {
    public Field createField() {
        Field field = new Field(getWidth(), getHeight());


        field.setArcs(createArcs());
        field.setDisk1(createDisk(1));
        field.setDisk2(createDisk(2));
        field.setWalls(createWalls());
        field.setHalfline(createHalfline());
        field.setBall(createBall());
        field.setGoals(createGoals());

        return field;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract Disk createDisk(int DiskNumber);

    protected abstract Ball createBall();

    protected abstract List<Halfline> createHalfline();

    protected abstract List<Goal> createGoals();

    protected abstract List<Arc> createArcs();
}
