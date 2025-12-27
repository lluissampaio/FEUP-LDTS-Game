package com.aor.airhockey.controller;

import com.aor.airhockey.gui.GUI;

import java.util.HashSet;
import java.util.Set;

public class MenuInputHandler implements InputHandler {
    private Set<GUI.ACTION> previousActions = new HashSet<>();

    @Override
    public Set<GUI.ACTION> processActions(Set<GUI.ACTION> actions) {
        Set<GUI.ACTION> newActions = new HashSet<>();
        for (GUI.ACTION action : actions) {
            if (!previousActions.contains(action)) {
               newActions.add(action);
            }
        }
        previousActions = newActions;
        return newActions;
    }
}
