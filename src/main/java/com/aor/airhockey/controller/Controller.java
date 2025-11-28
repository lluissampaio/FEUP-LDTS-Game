package com.aor.airhockey.controller;

import java.io.IOException;
import java.util.Set;

import com.aor.airhockey.Game;
import com.aor.airhockey.gui.GUI;

public abstract class Controller<T> {
    private final T model;

    public Controller(T model) {
        this.model = model;
    }

    public T getModel() {
        return model;
    }

    public abstract void step(Game game, Set<GUI.ACTION> actionSet, long time) throws IOException;
}
