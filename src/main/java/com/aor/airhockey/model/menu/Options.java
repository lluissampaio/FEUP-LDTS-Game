package com.aor.airhockey.model.menu;

import com.aor.airhockey.model.ColorSettings;

import java.util.Arrays;
import java.util.List;

public class Options {
    private final List<String> entries;
    private int currentEntry = 0;
    private final ColorSettings  colorSettings;

    private final List<String> colors;

    private int ballColorIndex;
    private int disk1ColorIndex;
    private int disk2ColorIndex;
    private int wallColorIndex;

    public Options(ColorSettings colorSettings) {
        this.entries = Arrays.asList("Ball color", "Disk1 color","Disk2 color", "Wall color", "Back");
        this.colorSettings = colorSettings;
        this.colors = Arrays.asList(
                "#FFFFFF", "#FF0000", "#00FF00", "#0000FF",
                "#FFFF00", "#FF00FF", "#00FFFF", "#FFA500",
                "#808080", "#800080", "#008080", "#FFC0CB");
        this.ballColorIndex = findColorIndex(colorSettings.getBallColor());
        this.disk1ColorIndex = findColorIndex(colorSettings.getDisk1Color());
        this.disk2ColorIndex = findColorIndex(colorSettings.getDisk2Color());
        this.wallColorIndex = findColorIndex(colorSettings.getWallColor());
    }

    private int findColorIndex(String color) {
        int index = colors.indexOf(color);
        return index;
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public void nextColor() {
        if (isSelectedBallColor()){
            ballColorIndex = (ballColorIndex + 1) % getNumberColors();
            colorSettings.setBallColor(colors.get(ballColorIndex));
        }
        if (isSelectedDisk1Color()){
            disk1ColorIndex = (disk1ColorIndex + 1) % getNumberColors();
            colorSettings.setDisk1Color(colors.get(disk1ColorIndex));
        }
        if (isSelectedDisk2Color()){
            disk2ColorIndex = (disk2ColorIndex + 1) % getNumberColors();
            colorSettings.setDisk2Color(colors.get(disk2ColorIndex));
        }
        if (isSelectedWallColor()){
            wallColorIndex = (wallColorIndex + 1) % getNumberColors();
            colorSettings.setWallColor(colors.get(wallColorIndex));
        }
    }

    public void previousColor() {
        if (isSelectedBallColor()){
            ballColorIndex = (ballColorIndex - 1 + getNumberColors()) % getNumberColors();
            colorSettings.setBallColor(colors.get(ballColorIndex));
        }
        if (isSelectedDisk1Color()){
            disk1ColorIndex = (disk1ColorIndex - 1 + getNumberColors()) % getNumberColors();
            colorSettings.setDisk1Color(colors.get(disk1ColorIndex));
        }
        if (isSelectedDisk2Color()){
            disk2ColorIndex =  (disk2ColorIndex - 1 + getNumberColors()) % getNumberColors();
            colorSettings.setDisk2Color(colors.get(disk2ColorIndex));
        }
        if (isSelectedWallColor()){
            wallColorIndex = (wallColorIndex - 1 + getNumberColors())  % getNumberColors();
            colorSettings.setWallColor(colors.get(wallColorIndex));
        }
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedBallColor() { return isSelected(0); }

    public boolean isSelectedDisk1Color() { return isSelected(1); }

    public boolean isSelectedDisk2Color() { return isSelected(2); }

    public boolean isSelectedWallColor() { return isSelected(3); }

    public boolean isSelectedExit() { return isSelected(4); }

    public int getNumberEntries() {
        return this.entries.size();
    }

    public int getNumberColors() {
        return this.colors.size();
    }

    public ColorSettings getColorSettings() {
        return colorSettings;
    }
}
