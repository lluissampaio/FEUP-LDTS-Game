package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Element;

public interface ElementViewer<T extends Element> {
    void draw(T element, GUI gui);
}