package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.states.MenuState;

import java.io.IOException;

public class FieldController extends GameController {
    private final Disk1Controller disk1Controller;
    private final Disk2Controller disk2Controller;
    private final BallController ballController;

    public FieldController(Field field, DiskController diskController, BallController ballController) {
        super(field);
        this.disk1Controller = new Disk1Controller(field);
        this.disk2Controller = new Disk2Controller(field);
        this.ballController = new BallController(field);
    }

    public void step(Game game, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT)
            game.setState(new MenuState(new Menu()));
        else {
            disk1Controller.step(game, action, time);
            disk2Controller.step(game, action, time);
            ballController.step(game, action, time);
        }
    }
}