package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.field.LoaderFieldBuilder;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.states.GameState;
import com.aor.airhockey.states.OptionsState;

import java.io.IOException;
import java.util.Set;

public class MenuController extends Controller<Menu> {
    public MenuController(Menu menu) {
        super(menu);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        for (GUI.ACTION action : actionSet) {
            switch (action) {
                case ARR_N, KEY_N:
                    getModel().previousEntry();
                    break;
                case ARR_S, KEY_S:
                    getModel().nextEntry();
                    break;
                case SELECT:
                    if (getModel().isSelectedExit()) game.setState(null);
                    // if (getModel().isSelectedSingleplayer()) game.setState(new GameState(new LoaderFieldBuilder().createField()));
                    // SINGLEPLAYER IS YET TO IMPLEMENT
                    if (getModel().isSelectedMultiplayer()) game.setState(new GameState(new LoaderFieldBuilder().createField()));
                    if (getModel().isSelectedOptions()) game.setState(new OptionsState(game.getColorSettings()));
            }
        }
    }
}
