package com.aor.airhockey.config.gui;

import com.aor.airhockey.model.Position;

public class LanternaGUIConfig {
    public static final String BACKGROUND_COLOR = "#0F172A";

    public static final int FONT_SIZE = 5;
    public static final String FONT_PATH = "fonts/square.ttf";

    public static final String DEFAULT_DISK_COLOR = "#FFFFFF";

    public static final String HALFLINE_COLOR = "#808080";

    public static final String SCORE_COLOR = "#FFFFFF";
    public static final String SCORE_STRING = "P1 [%d - %d] P2";
    public static final Position SCORE_POSITION = new Position(98, 0);

    public static final String TIMER_COLOR = "#FFFFFF";
    public static final String TIMER_STRING = "%02d:%02d"; // min:seconds
    public static final String OVERTIME_STRING = "OVERTIME!";
    public static final String OVERTIME_COLOR = "#FFFF00";
    public static final Position TIMER_POSITION = new Position(0, 0);

    // CHANGE TIMER TO SECONDS AND CHANGE COLOR ACCORDINGLY
}
