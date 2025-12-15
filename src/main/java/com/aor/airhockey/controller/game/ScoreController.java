package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Score;
import com.aor.airhockey.config.GameConfig;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.controller.SoundManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Set;

public class ScoreController extends GameController {
    private SoundManager soundManager;
    public ScoreController(Field field) {
        super(field);
        try {
            soundManager = new SoundManager();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (getModel().getBall().getPosition().getX() < 0) // Player 2 scores
        {
            soundManager.playGoal();
            getModel().getScore().incrementPlayer2Points();
            getModel().getBall().player2Scored();
        }
        else if (getModel().getBall().getPosition().getX() > GameConfig.GAME_WIDTH)  // Player 1 scores
        {
            soundManager.playGoal();
            getModel().getScore().incrementPlayer1Points();
            getModel().getBall().player1Scored();
        }
    }
}
