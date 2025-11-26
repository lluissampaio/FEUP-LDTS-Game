package com.aor.airhockey.model.game.field;

import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.elements.Wall;

import java.util.List;

public abstract class FieldBuilder {
    public Field createField() {
        Field field = new Field(getWidth(), getHeight());

        field.setBall(createBall());
        field.setDisk1(createDisk(1));
        field.setDisk2(createDisk(2));
        field.setWalls(createWalls());

        return field;
    }

    protected abstract int getWidth();

    protected abstract int getHeight();

    protected abstract List<Wall> createWalls();

    protected abstract Disk createDisk(int DiskNumber);

    protected abstract Ball createBall();
}
