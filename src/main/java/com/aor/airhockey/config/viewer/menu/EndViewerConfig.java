package com.aor.airhockey.config.viewer.menu;

import com.aor.airhockey.config.ConfigCalculator;
import com.aor.airhockey.config.model.menu.EndConfig;
import com.aor.airhockey.model.menu.End;

import java.util.List;

public class EndViewerConfig {
    public static final String FINAL_SCORE = "%d : %d";
    public static final int FINAL_SCORE_X_POS = ConfigCalculator.calculateStringCenterX(FINAL_SCORE);
    public static final int FINAL_SCORE_Y_POS = 20;
    public static final String SCORE_COLOR = "#FFFF00";
    public static final String TITLE_TEXT = "PLAYER %d WINS!";
    public static final int TITLE_X_POS = ConfigCalculator.calculateStringCenterX(TITLE_TEXT);
    public static final int TITLE_Y_POS = 40;
    public static final String TITLE_COLOR = "#018ABE";

    public static final List<Integer> POSITIONS_LIST = ConfigCalculator.calculateStringListCenterX(EndConfig.ENTRIES);

    public static final int OPTIONS_Y_STARTING_POS = 64;
    public static final int OPTIONS_Y_DISTANCE = 24;

    public static final String IS_SELECTED_COLOR = "#FF3333";
    public static final String NOT_SELECTED_COLOR = "#FFFFFF";
}
