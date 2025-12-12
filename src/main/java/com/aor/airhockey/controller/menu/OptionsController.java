package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.states.MenuState;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class OptionsController extends Controller<Options> {
    private Set<GUI.ACTION> previousActions = new HashSet<>();
    public OptionsController(Options options) {
        super(options);
    }

    @Override
    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        for (GUI.ACTION action : actionSet) {
            if (!previousActions.contains(action)) {
                switch (action) {
                    case ARR_N, KEY_N:
                        getModel().previousEntry();
                        break;
                    case ARR_S, KEY_S:
                        getModel().nextEntry();
                        break;
                    case ARR_E, KEY_E:
                        getModel().nextColor();
                        break;
                    case ARR_W, KEY_W:
                        getModel().previousColor();
                        break;
                    case SELECT:
                        if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
                }
            }
        }
        previousActions = actionSet;
    }
}
