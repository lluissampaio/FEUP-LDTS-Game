package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.GameSounds;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.config.GameConfig;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.controller.SoundManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Set;

public class ScoreController extends GameController {
    private final GameSounds gameSounds;
    public ScoreController(Field field) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(field);
        gameSounds = new SoundManager();
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (getModel().getBall().getPosition().getX() < 0) // Player 2 scores
        {
            gameSounds.playGoal();
            getModel().getScore().incrementPlayer2Points();
            getModel().getBall().player2Scored();
        }
        else if (getModel().getBall().getPosition().getX() > GameConfig.GAME_WIDTH)  // Player 1 scores
        {
            gameSounds.playGoal();
            getModel().getScore().incrementPlayer1Points();
            getModel().getBall().player1Scored();
        }
    }
}
