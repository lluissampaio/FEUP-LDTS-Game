package com.aor.airhockey.model.menu;

import com.aor.airhockey.config.model.menu.MenuConfig;

import java.util.List;

public class Menu {
    private final List<String> entries;
    private int currentEntry = 0;

    public Menu() {
        this.entries = MenuConfig.ENTRIES;
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

    public boolean isSelectedExit() { return isSelected(3); }

    public boolean isSelectedSingleplayer() { return isSelected(0); }

    public boolean isSelectedMultiplayer() { return isSelected(1); }

    public boolean isSelectedOptions() { return isSelected(2); }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
