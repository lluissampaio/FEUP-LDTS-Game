package com.aor.airhockey.config.viewer.menu;

import com.aor.airhockey.config.ConfigCalculator;
import com.aor.airhockey.config.model.menu.MenuConfig;
import com.aor.airhockey.config.model.menu.OptionsConfig;

import java.util.Arrays;
import java.util.List;

public class MenuViewerConfig {
    public static final String TITLE_TEXT = "AIR HOCKEY";
    public static final int TITLE_X_POS = ConfigCalculator.calculateStringCenterX(TITLE_TEXT);
    public static final int TITLE_Y_POS = 20;
    public static final String TITLE_COLOR = "#018ABE";

    public static final List<Integer> POSITIONS_LIST = ConfigCalculator.calculateStringListCenterX(MenuConfig.ENTRIES);

    public static final int OPTIONS_Y_STARTING_POS = 56;
    public static final int OPTIONS_Y_DISTANCE = 20;

    public static final String IS_SELECTED_COLOR = "#FF3333";
    public static final String NOT_SELECTED_COLOR = "#FFFFFF";
}
