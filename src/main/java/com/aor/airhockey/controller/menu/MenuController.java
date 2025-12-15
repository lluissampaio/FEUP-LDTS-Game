package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.field.LoaderFieldBuilder;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.states.GameState;
import com.aor.airhockey.states.OptionsState;
import com.aor.airhockey.controller.SoundManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MenuController extends Controller<Menu> {
    private Set<GUI.ACTION> previousActions = new HashSet<>();
    private SoundManager soundManager;
    public MenuController(Menu menu) {
        super(menu);
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
                    case ARR_UP, KEY_W:
                        soundManager.playMenuScroll();
                        getModel().previousEntry();
                        break;
                    case ARR_DOWN, KEY_S:
                        soundManager.playMenuScroll();
                        getModel().nextEntry();
                        break;
                    case SELECT:
                        soundManager.playMenuSelect();
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
        previousActions = actionSet;
    }
}
