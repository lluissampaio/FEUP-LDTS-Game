package com.aor.airhockey.model;
import com.aor.airhockey.config.model.ColorSettingsConfig;

public class ColorSettings {
    private String ballColor;
    private String disk1Color;
    private String disk2Color;
    private String wallColor;

    public ColorSettings() {
        this.ballColor = ColorSettingsConfig.DEFAULT_BALL_COLOR;
        this.disk1Color = ColorSettingsConfig.DEFAULT_DISK1_COLOR;
        this.disk2Color = ColorSettingsConfig.DEFAULT_DISK2_COLOR;
        this.wallColor = ColorSettingsConfig.DEFAULT_WALL_COLOR;
    }

    public String getBallColor() {
        return ballColor;
    }
    public void setBallColor(String ballColor) {
        this.ballColor = ballColor;
    }

    public String getDisk1Color() {
        return disk1Color;
    }
    public void setDisk1Color(String disk1Color) {
        this.disk1Color = disk1Color;
    }

    public String getDisk2Color() {
        return disk2Color;
    }
    public void setDisk2Color(String disk2Color) {
        this.disk2Color = disk2Color;
    }

    public String getWallColor() {
        return wallColor;
    }
    public void setWallColor(String wallColor) {
        this.wallColor = wallColor;
    }
}
