package com.aor.airhockey.model.game.field;

import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.elements.Wall;

import java.util.List;

public abstract class FieldBuilder {
    public Field createArena() {
        Field field = new Field(getWidth(), getHeight());

        field.setBall(createBall());
        field.setDisk1(createDisk());
        field.setDisk2(createDisk());
        field.setWalls(createWalls());

        return field;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract Ball createBall();

    protected abstract Disk createDisk();

    protected abstract List<Wall> createWalls();
}
