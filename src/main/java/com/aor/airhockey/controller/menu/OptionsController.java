package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.SoundManager;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.states.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OptionsController extends Controller<Options> {
    private Set<GUI.ACTION> previousActions = new HashSet<>();
    private SoundManager soundManager;
    public OptionsController(Options options) {
        super(options);
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
        for (GUI.ACTION action : actionSet) {
            if (!previousActions.contains(action)) {
                switch (action) {
                    case ARR_N, KEY_N:
                        soundManager.playMenuScroll();
                        getModel().previousEntry();
                        break;
                    case ARR_S, KEY_S:
                        soundManager.playMenuScroll();
                        getModel().nextEntry();
                        break;
                    case ARR_E, KEY_E:
                        soundManager.playMenuScroll();
                        getModel().nextColor();
                        break;
                    case ARR_W, KEY_W:
                        soundManager.playMenuScroll();
                        getModel().previousColor();
                        break;
                    case SELECT:
                        soundManager.playMenuSelect();
                        if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
                }
            }
        }
        previousActions = actionSet;
    }
}
