package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.InputHandler;
import com.aor.airhockey.controller.MenuInputHandler;
import com.aor.airhockey.controller.SoundManager;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.states.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Set;

public class OptionsController extends Controller<Options> {
    private InputHandler inputHandler;
    private SoundManager soundManager;
    public OptionsController(Options options) {
        super(options);
        inputHandler = new MenuInputHandler();
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
        Set<GUI.ACTION> newActions = inputHandler.processActions(actionSet);
        for (GUI.ACTION action : newActions) {
            switch (action) {
                case ARR_UP, KEY_W:
                    soundManager.playMenuScroll();
                    getModel().previousEntry();
                    break;
                case ARR_DOWN, KEY_S:
                    soundManager.playMenuScroll();
                    getModel().nextEntry();
                    break;
                case ARR_RIGHT, KEY_D:
                    soundManager.playMenuScroll();
                    getModel().nextColor();
                    break;
                case ARR_LEFT, KEY_A:
                    soundManager.playMenuScroll();
                    getModel().previousColor();
                    break;
                case SELECT:
                    if (getModel().isSelectedExit()) {
                        soundManager.playMenuSelect();
                        game.setState(new MenuState(new Menu()));
                    }
            }

        }
    }
}
