package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Score;

public class ScoreViewer implements ElementViewer<Score> {
    @Override
    public void draw(Score score, GUI gui) { gui.drawScore(score, gui); }
}
