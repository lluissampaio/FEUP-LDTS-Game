package com.aor.airhockey.model.menu;

import java.util.Arrays;
import java.util.List;

public class Options {
    private final List<String> entries;
    private int currentEntry = 0;

    public Options() {
        this.entries = Arrays.asList("Ball color", "Disk color", "Wall color", "Exit");
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

    public boolean isSelectedDiskColor() { return isSelected(1); }

    public boolean isSelectedWallColor() { return isSelected(2); }

    public boolean isSelectedExit() { return isSelected(3); }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
