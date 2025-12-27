package com.aor.airhockey.states;

import com.aor.airhockey.controller.Controller;
import com.aor.airhockey.controller.game.FieldController;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.viewer.game.GameViewer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class GameState extends State<Field> {
    public GameState(Field field) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        super(field);
    }

    @Override
    protected Viewer<Field> getViewer() { return new GameViewer(getModel()); }

    @Override
    protected Controller<Field> getController() throws UnsupportedAudioFileException, LineUnavailableException, IOException { return new FieldController(getModel()); }
}
