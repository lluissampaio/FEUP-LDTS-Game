package com.aor.airhockey.controller.game;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.field.Field;

import java.io.IOException;

public class Disk2Controller extends GameController {
    public Disk2Controller(Field field){ super(field); }

    public void moveDisk1Left() {
        moveDisk2(getModel().getDisk2().getPosition().getLeft());
    }

    public void moveDisk1Right() {
        moveDisk2(getModel().getDisk2().getPosition().getRight());
    }

    public void moveDisk1Up() {
        moveDisk2(getModel().getDisk2().getPosition().getUp());
    }

    public void moveDisk1Down() {
        moveDisk2(getModel().getDisk2().getPosition().getDown());
    }

    private void moveDisk2(Position position) {
        if (getModel().isEmpty(position)) {
            getModel().getDisk1().setPosition(position);
        }
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (action == GUI.ACTION.UP) moveDisk1Up();
        if (action == GUI.ACTION.RIGHT) moveDisk1Right();
        if (action == GUI.ACTION.DOWN) moveDisk1Down();
        if (action == GUI.ACTION.LEFT) moveDisk1Left();
    }
}
