package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.game.FieldController;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.game.GameViewer;

public class GameState extends State<Field> {
    public GameState(Field field) {
        super(field);
    }

    @Override
    protected Viewer<Field> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Field> getController() {
        return new FieldController(getModel());
    }
}
