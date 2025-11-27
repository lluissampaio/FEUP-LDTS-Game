package com.aor.airhockey.controller.menu;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.menu.Options;

import java.io.IOException;

public class OptionsController extends Controller<Options> {
    public OptionsController(Options options) {
        super(options);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case ARR_N, KEY_N:
                getModel().previousEntry();
                break;
            case ARR_S, KEY_S:
                getModel().nextEntry();
                break;
        }
    }
}
