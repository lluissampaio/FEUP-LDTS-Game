package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.*;
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
    private MenuSounds menuSounds;
    public OptionsController(Options options) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(options);
        inputHandler = new MenuInputHandler();
        menuSounds = new SoundManager();
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Set<GUI.ACTION> newActions = inputHandler.processActions(actionSet);
        for (GUI.ACTION action : newActions) {
            switch (action) {
                case ARR_UP, KEY_W:
                    menuSounds.playMenuScroll();
                    getModel().previousEntry();
                    break;
                case ARR_DOWN, KEY_S:
                    menuSounds.playMenuScroll();
                    getModel().nextEntry();
                    break;
                case ARR_RIGHT, KEY_D:
                    menuSounds.playMenuScroll();
                    getModel().nextColor();
                    break;
                case ARR_LEFT, KEY_A:
                    menuSounds.playMenuScroll();
                    getModel().previousColor();
                    break;
                case SELECT:
                    if (getModel().isSelectedExit()) {
                        menuSounds.playMenuSelect();
                        game.setState(new MenuState(new Menu()));
                    }
            }

        }
    }
}
