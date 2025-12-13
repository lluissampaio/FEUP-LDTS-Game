package com.aor.airhockey.gui;

import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Score;

import java.io.IOException;
import java.util.Set;

public interface GUI {
    Set<ACTION> getNextActions() throws IOException;

    void drawBall(Position position, double radius);

    void drawWall(Position position);

    void drawDisk(Position position, double radius, int playerNumber);

    void drawHalfline(Position position);

    void drawScore(Score score, GUI gui);

    void drawText(Position position, String text, String color);

    void drawSquare(int x, int y, String color);

    void drawCircle(Position position, int radius, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    // ARR -> stands for the arrow keys actions
    // KEY -> stands for the "wasd" keys actions
    enum ACTION { ARR_N, ARR_E, ARR_S, ARR_W, KEY_N, KEY_E, KEY_S, KEY_W, NONE, QUIT, SELECT}
}

//    N
// W     E
//    S