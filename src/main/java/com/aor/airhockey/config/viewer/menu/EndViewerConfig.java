package com.aor.airhockey.config.viewer.menu;

import com.aor.airhockey.config.ConfigCalculator;
import com.aor.airhockey.config.model.menu.EndConfig;
import com.aor.airhockey.config.model.menu.MenuConfig;

import java.util.Arrays;
import java.util.List;

public class EndViewerConfig {
    public static final String TITLE_TEXT = "Player %d wins!";
    public static final int TITLE_X_POS = ConfigCalculator.calculateStringCenterX(TITLE_TEXT);
    public static final int TITLE_Y_POS = 20;
    public static final String TITLE_COLOR = "#018ABE";

    public static final List<Integer> POSITIONS_LIST = ConfigCalculator.calculateStringListCenterX(EndConfig.ENTRIES);

    public static final int OPTIONS_Y_STARTING_POS = 48;
    public static final int OPTIONS_Y_DISTANCE = 12;

    public static final String IS_SELECTED_COLOR = "#FF3333";
    public static final String NOT_SELECTED_COLOR = "#FFFFFF";
}
