package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.MenuController;
import com.aor.airhockey.controller.menu.OptionsController;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.MenuViewer;
import com.aor.airhockey.viewer.menu.OptionsViewer;

public class OptionsState extends State<Options> {
    public OptionsState(Options options) {
        super(options);
    }

    @Override
    protected Viewer<Options> getViewer() {
        return new OptionsViewer(getModel());
    }

    @Override
    protected Controller<Options> getController() {
        return new OptionsController(getModel());
    }
}
