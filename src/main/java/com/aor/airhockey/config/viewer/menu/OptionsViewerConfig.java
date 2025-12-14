package com.aor.airhockey.config.viewer.menu;

import com.aor.airhockey.config.ConfigCalculator;
import com.aor.airhockey.config.GameConfig;
import com.aor.airhockey.config.model.menu.MenuConfig;
import com.aor.airhockey.config.model.menu.OptionsConfig;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;

public class OptionsViewerConfig {
    public static final String OPTIONS_TITLE = "OPTIONS";
    public static final int OPTIONS_X_POS = ConfigCalculator.calculateStringCenterX(OPTIONS_TITLE);
    public static final int OPTIONS_Y_POS = 20;
    public static final String OPTIONS_COLOR = "#018ABE";

    public static final List<Integer> POSITIONS_LIST = ConfigCalculator.calculateStringListCenterX(OptionsConfig.ENTRIES);

    public static final int OPTIONS_Y_STARTING_POS = 56;
    public static final int OPTIONS_Y_DISTANCE = 16;

    public static final String IS_SELECTED_COLOR = "#FF3333";
    public static final String NOT_SELECTED_COLOR = "#FFFFFF";

    public static final String DEFAULT_COLOR = "#FFFFFF";

    public static final int SQUARE_X_POS = 200;
    public static final int SQUARE_Y_STARTING_POS = 56;
    public static final int SQUARE_Y_DISTANCE = 16;
    public static final int SQUARE_LENGTH = 8;
}
