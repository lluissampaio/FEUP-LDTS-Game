package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.model.menu.End;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.states.EndState;
import com.aor.airhockey.states.MenuState;

import java.io.IOException;
import java.util.Set;

public class FieldController extends GameController {
    private final Disk1Controller disk1Controller;
    private final Disk2Controller disk2Controller;
    private final BallController ballController;
    private final ScoreController scoreController;

    public FieldController(Field field) {
        super(field);

        this.disk1Controller = new Disk1Controller(field);
        this.disk2Controller = new Disk2Controller(field);
        this.ballController = new BallController(field);
        this.scoreController = new ScoreController(field);
    }

    public void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException {
        if (actionSet.contains(GUI.ACTION.QUIT)) {
            game.setState(new MenuState(new Menu()));
        }
        else {
            disk1Controller.step(game, actionSet, time);
            disk2Controller.step(game, actionSet, time);
            ballController.step(game, actionSet, time);
            scoreController.step(game, actionSet, time);
        }

        // check if there is a winner
        if (getModel().getWinner() != 0) {
            game.setState(new EndState(new End(getModel().getWinner(), getModel().getScore().getPlayer1Points(), getModel().getScore().getPlayer2Points())));
        }
    }
}