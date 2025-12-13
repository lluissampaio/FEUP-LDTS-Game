package com.aor.airhockey.model.game.field;

import com.aor.airhockey.config.gui.LanternaGUIConfig;
import com.aor.airhockey.config.model.game.field.FieldConfig;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.*;

import java.util.List;

public class Field {
    private final int width;
    private final int height;

    // time
    private final int duration;
    private final long startTime;

    private List<Wall> walls;
    private Ball ball;
    private Disk disk1;
    private Disk disk2;
    private List<Halfline> halfline;
    private List<Goal> goals;
    private Score score;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.score = new Score(LanternaGUIConfig.SCORE_POSITION.getX(), LanternaGUIConfig.SCORE_POSITION.getY());
        this.duration = FieldConfig.GAME_DURATION;
        this.startTime = System.currentTimeMillis();
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

    public void setScore(Score score) { this.score = score; }
    public Score getScore() { return this.score; }

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

    public boolean isWall(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return true;
        return false;
    }

    public int getWinner() {
        // check if we are at over time
        if (isOvertime()) {
            if (score.getPlayer1Points() > score.getPlayer2Points()) return 1;
            else if (score.getPlayer2Points() > score.getPlayer1Points()) return 2;
            else return 0;
        }
        else {
            if (score.getPlayer1Points() >= 4) return 1; // player 1 wins
            if (score.getPlayer2Points() >= 4) return 2; // player 2 wins
            return 0; // no winner
        }
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public boolean isOvertime() {
        return getElapsedTime() >= FieldConfig.GAME_DURATION;
    }
}
