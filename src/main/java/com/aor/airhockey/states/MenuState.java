package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.MenuController;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
