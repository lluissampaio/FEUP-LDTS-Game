package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.OptionsController;
import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.OptionsViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class OptionsState extends State<Options> {
    public OptionsState(Options options) throws UnsupportedAudioFileException, LineUnavailableException, IOException {super(options);}

    @Override
    protected Viewer<Options> getViewer() {
        return new OptionsViewer(getModel());
    }

    @Override
    protected Controller<Options> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        return new OptionsController(getModel());
    }
}
