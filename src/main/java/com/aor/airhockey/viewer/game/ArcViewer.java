package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Arc;

public class ArcViewer implements ElementViewer<Arc> {
    @Override
    public void draw(Arc arc, GUI gui) {
        gui.drawArc(arc.getPosition());
    }
}
