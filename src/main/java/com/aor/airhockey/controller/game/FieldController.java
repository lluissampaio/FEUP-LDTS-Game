package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.states.MenuState;

import java.io.IOException;

public class FieldController extends GameController {
    private final DiskController diskController;
    private final BallController ballController;

    public FieldController(Field field, DiskController diskController, BallController ballController) {
        super(field);
        this.diskController = new DiskController(field);
        this.ballController = new BallController(field);
    }

    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT)
            game.setState(new MenuState(new Menu()));
        else {
            heroController.step(game, action, time);
            monsterController.step(game, action, time);
        }
    }
}