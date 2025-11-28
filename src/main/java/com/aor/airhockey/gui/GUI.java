package com.aor.airhockey.gui;

import com.aor.airhockey.model.Position;

import java.io.IOException;
import java.util.Set;

public interface GUI {
    Set<ACTION> getNextActions() throws IOException;

    void drawBall(Position position);

    void drawWall(Position position);

    void drawDisk(Position position);

    void drawHalfline(Position position);

    void drawText(Position position, String text, String color);

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