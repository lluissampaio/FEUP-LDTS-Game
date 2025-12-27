package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.*;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.field.LoaderFieldBuilder;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.states.GameState;
import com.aor.airhockey.states.OptionsState;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Set;

public class MenuController extends Controller<Menu> {
    private InputHandler inputHandler;
    private MenuSounds menuSounds;
    public MenuController(Menu menu) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(menu);
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
                case SELECT:
                    menuSounds.playMenuSelect();
                    if (getModel().isSelectedExit()) game.setState(null);
                    // if (getModel().isSelectedSingleplayer()) game.setState(new GameState(new LoaderFieldBuilder().createField()));
                    // SINGLEPLAYER IS YET TO IMPLEMENT
                    if (getModel().isSelectedMultiplayer())
                        game.setState(new GameState(new LoaderFieldBuilder().createField()));
                    if (getModel().isSelectedOptions())
                        game.setState(new OptionsState(new Options(game.getColorSettings())));
            }

        }
    }
}
