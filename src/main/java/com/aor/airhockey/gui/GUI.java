package com.aor.airhockey.gui;

import com.aor.airhockey.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawBall(Position position);

    void drawWall(Position position);

    void drawDisk(Position position);

    void drawHalfline(Position position);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, UPRIGHT, RIGHT, DOWNRIGHT, DOWN, DOWNLEFT, LEFT, UPLEFT, NONE, QUIT, SELECT}
}
