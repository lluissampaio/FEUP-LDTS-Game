package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.MenuController;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.MenuViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new MenuController(getModel());
    }
}
