package com.aor.airhockey.config;

import com.aor.airhockey.config.GameConfig;
import com.aor.airhockey.config.gui.FontRendererConfig;
import com.aor.airhockey.config.gui.LanternaGUIConfig;

import java.util.ArrayList;
import java.util.List;

// this class only calculates the correct position for a string to begin so that it is centralized
public class ConfigCalculator {
    public static int calculateCenterX() { return GameConfig.GAME_WIDTH / 2; }
    public static int calculateCenterY() { return GameConfig.GAME_HEIGHT / 2; }

    public static int calculateStringCenterX(String string) { return ((GameConfig.GAME_WIDTH - string.length()*FontRendererConfig.CHAR_WIDTH) / 2); }

    public static List<Integer> calculateStringListCenterX(List<String> stringList) {
        List<Integer> centers = new ArrayList<>();

        for (String string : stringList) {
            centers.add(calculateStringCenterX(string));
        }

        return centers;
    }
}
