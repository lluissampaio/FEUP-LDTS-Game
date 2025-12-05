package com.aor.airhockey.model.menu;

import java.util.Arrays;
import java.util.List;

public class Options {
    private final List<String> entries;
    private int currentEntry = 0;

    public Options() {
        this.entries = Arrays.asList("Ball color", "Disk1 color","Disk2 color", "Wall color", "Back");
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
}
