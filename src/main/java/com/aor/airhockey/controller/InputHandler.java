package com.aor.airhockey.controller;

import com.aor.airhockey.gui.GUI;
import java.util.Set;

public interface InputHandler {
    Set<GUI.ACTION> processActions(Set<GUI.ACTION> actions);
}
