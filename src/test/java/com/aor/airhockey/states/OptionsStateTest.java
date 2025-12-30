package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.menu.OptionsController;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.menu.OptionsViewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class OptionsStateTest {

    @Test
    void verify() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Options model = Mockito.mock(Options.class);
        OptionsState state = new OptionsState(model);

        Viewer<Options> viewer = state.getViewer();
        Controller<Options> controller = state.getController();

        Assertions.assertTrue(viewer instanceof OptionsViewer);
        Assertions.assertTrue(controller instanceof OptionsController);
        Assertions.assertEquals(model, state.getModel());

    }
}
