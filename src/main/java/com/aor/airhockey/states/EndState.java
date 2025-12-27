package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.game.FieldController;
import com.aor.airhockey.controller.menu.EndController;
import com.aor.airhockey.controller.menu.MenuController;
import com.aor.airhockey.model.menu.End;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.EndViewer;
import com.aor.airhockey.viewer.menu.MenuViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class EndState extends State<End> {
    public EndState(End end) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(end);
    }

    @Override
    protected Viewer<End> getViewer() {
        return new EndViewer(getModel());
    }

    @Override
    protected Controller<End> getController() {
        return new EndController(getModel());
    }
}
