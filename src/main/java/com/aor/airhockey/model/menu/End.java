package com.aor.airhockey.model.menu;

import com.aor.airhockey.config.model.menu.EndConfig;

import java.util.List;

public class End {
    private final List<String> entries;
    private int currentEntry = 0;

    public End() { this.entries = EndConfig.ENTRIES; }

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

    public boolean isSelectedExit() { return isSelected(2); }

    public boolean isSelectedGoToMenu() { return isSelected(1); }

    public boolean isSelectedPlayAgain() { return isSelected(0); }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
