package com.aor.airhockey.model.game.field;

import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.elements.Wall;

import java.util.List;

public class Field {
    private final int width;
    private final int height;

    private List<Wall> walls;
    private Ball ball;
    private Disk disk1;
    private Disk disk2;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public Ball getBall() { return ball; }

    public Disk getDisk1() { return disk1; }
    public Disk getDisk2() { return disk2; }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setBall(Ball ball) {this.ball = ball;}

    public void setDisk1(Disk disk1) {this.disk1 = disk1;}
    public void setDisk2(Disk disk2) {this.disk2 = disk2;}

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }
}
