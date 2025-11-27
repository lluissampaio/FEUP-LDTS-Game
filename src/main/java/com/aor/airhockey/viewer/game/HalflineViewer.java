package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Halfline;

public class HalflineViewer implements ElementViewer<Halfline> {
    @Override
    public void draw(Halfline halfline, GUI gui){ gui.drawHalfline(halfline.getPosition()); }
}
