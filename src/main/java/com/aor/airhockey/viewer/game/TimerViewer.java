package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Timer;

public class TimerViewer implements ElementViewer<Timer>{
    @Override
    public void draw(Timer timer, GUI gui) {
        gui.drawTimer(timer, gui);
    }
}
