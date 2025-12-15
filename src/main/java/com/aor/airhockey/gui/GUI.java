package com.aor.airhockey.gui;

import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Score;
import com.aor.airhockey.model.game.elements.Timer;

import java.io.IOException;
import java.util.Set;

public interface GUI {
    Set<ACTION> getNextActions() throws IOException;

    void drawBall(Position position, double radius);

    void drawWall(Position position);

    void drawDisk(Position position, double radius, int playerNumber);

    void drawHalfline(Position position);

    void drawScore(Score score, GUI gui);

    void drawTimer(Timer timer, GUI gui);

    void drawText(Position position, String text, String color);

    void drawSquare(int x, int y, String color);

    void drawCircle(Position position, int radius, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    // ARR -> stands for the arrow keys actions
    // KEY -> stands for the "wasd" keys actions
    enum ACTION { ARR_UP, ARR_RIGHT, ARR_DOWN, ARR_LEFT, KEY_W, KEY_D, KEY_S, KEY_A, NONE, QUIT, SELECT, KEY_R}
}

//    N
// W     E
//    S