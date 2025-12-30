package com.aor.airhockey.model;

import com.aor.airhockey.config.model.ColorSettingsConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ColorSettingsTest {

    @Test
    void defaultValues() {
        ColorSettings settings = new ColorSettings();

        Assertions.assertEquals(ColorSettingsConfig.DEFAULT_BALL_COLOR, settings.getBallColor());
        Assertions.assertEquals(ColorSettingsConfig.DEFAULT_DISK1_COLOR, settings.getDisk1Color());
        Assertions.assertEquals(ColorSettingsConfig.DEFAULT_DISK2_COLOR, settings.getDisk2Color());
        Assertions.assertEquals(ColorSettingsConfig.DEFAULT_WALL_COLOR, settings.getWallColor());
    }

    @Test
    void settersAndGetters() {
        ColorSettings settings = new ColorSettings();

        settings.setBallColor("#FFFFFF");
        Assertions.assertEquals("#FFFFFF", settings.getBallColor());

        settings.setDisk1Color("#FF0000");
        Assertions.assertEquals("#FF0000", settings.getDisk1Color());

        settings.setDisk2Color("#00FF00");
        Assertions.assertEquals("#00FF00", settings.getDisk2Color());

        settings.setWallColor("#000000");
        Assertions.assertEquals("#000000", settings.getWallColor());
    }
}