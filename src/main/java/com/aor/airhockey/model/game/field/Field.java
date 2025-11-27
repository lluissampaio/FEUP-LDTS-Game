package com.aor.airhockey.model.game.field;

import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.*;

import java.util.List;

public class Field {
    private final int width;
    private final int height;

    private List<Wall> walls;
    private Ball ball;
    private Disk disk1;
    private Disk disk2;
    private List<Halfline> halfline;
    private List<Goal> goals;

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

    public List<Halfline> getHalfline() {return halfline; }

    public List<Goal> getGoals() { return goals; }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setBall(Ball ball) {this.ball = ball;}

    public void setDisk1(Disk disk1) {this.disk1 = disk1;}
    public void setDisk2(Disk disk2) {this.disk2 = disk2;}

    public void setHalfline(List<Halfline> halfline) {this.halfline = halfline;}

    public void setGoals(List<Goal> goals) {this.goals = goals;}

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        for (Halfline halfline : halfline)
            if (halfline.getPosition().equals(position))
                return false;
        for (Goal goal : goals)
            if (goal.getPosition().equals(position))
                return false;
        return true;
    }
}
