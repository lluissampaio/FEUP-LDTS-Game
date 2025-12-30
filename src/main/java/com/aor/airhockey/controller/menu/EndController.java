package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.*;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.field.LoaderFieldBuilder;
import com.aor.airhockey.model.menu.End;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.states.GameState;
import com.aor.airhockey.states.MenuState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Set;

public class EndController extends Controller<End> {
    private InputHandler inputHandler;
    private MenuSounds menuSounds;
    public EndController(End end) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this(end, new MenuInputHandler(), new SoundManager());
    }

    public EndController(End end, InputHandler inputHandler, MenuSounds menuSounds) {
        super(end);
        this.inputHandler = inputHandler;
        this.menuSounds = menuSounds;
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
                case SELECT:
                    menuSounds.playMenuSelect();
                    if (getModel().isSelectedExit()) game.setState(null);
                    // if (getModel().isSelectedSingleplayer()) game.setState(new GameState(new LoaderFieldBuilder().createField()));
                    // SINGLEPLAYER IS YET TO IMPLEMENT
                    if (getModel().isSelectedPlayAgain())
                        game.setState(new GameState(new LoaderFieldBuilder().createField()));
                    if (getModel().isSelectedGoToMenu())
                        game.setState(new MenuState(new Menu()));
                }
        }
    }
}
