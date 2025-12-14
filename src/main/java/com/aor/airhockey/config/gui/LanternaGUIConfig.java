package com.aor.airhockey.config.gui;

import com.aor.airhockey.model.Position;

public class LanternaGUIConfig {
    public static final String BACKGROUND_COLOR = "#0F172A";

    public static final int FONT_SIZE = 20;
    public static final String FONT_PATH = "fonts/square.ttf"; // inside resources

    public static final String DEFAULT_DISK_COLOR = "#FFFFFF";

    public static final String HALFLINE_COLOR = "#808080";

    public static final String SCORE_COLOR = "#FFFFFF";
    public static final String SCORE_STRING = "Player 1 [%d - %d] Player 2";
    public static final Position SCORE_POSITION = new Position(25, 0);

    public static final String TIMER_COLOR = "#FFFFFF";
    public static final String TIMER_STRING = "Timer: %d"; // for now the time will appear in milliseconds;
    public static final Position TIMER_POSITION = new Position(0, 0);

    // CHANGE TIMER TO SECONDS AND CHANGE COLOR ACCORDINGLY
}
