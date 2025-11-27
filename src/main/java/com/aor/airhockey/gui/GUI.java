package com.aor.airhockey.gui;

import com.aor.airhockey.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;

    void drawBall(Position position);

    void drawWall(Position position);

    void drawDisk(Position position);

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    // ARR -> stands for the arrow keys actions
    // KEY -> stands for the "wasd" keys actions
    enum ACTION { ARR_N, ARR_NE , ARR_E, ARR_SE, ARR_S, ARR_SW, ARR_W, ARR_NW,
            KEY_N, KEY_NE, KEY_E, KEY_SE, KEY_S, KEY_SW, KEY_W, KEY_NW,
                NONE, QUIT, SELECT}
}

// NW  N  NE
// W      E
// SW  S  SE