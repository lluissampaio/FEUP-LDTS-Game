package com.aor.airhockey.controller.game;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.model.game.field.Field;

public abstract class GameController extends Controller<Field> {
    public GameController(Field field) {
        super(field);
    }
}
