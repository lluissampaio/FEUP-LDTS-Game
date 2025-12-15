package com.aor.airhockey;

import com.aor.airhockey.controller.SoundManager;
import com.aor.airhockey.gui.LanternaGUI;
import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.states.*;
import com.aor.airhockey.config.GameConfig;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {
    private final LanternaGUI gui;
    private State state;
    private final ColorSettings  colorSettings;
    private SoundManager soundManager;

    public Game() throws FontFormatException, IOException, URISyntaxException {
        this.colorSettings = new ColorSettings();
        this.gui = new LanternaGUI(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT, getColorSettings());
        this.state = new MenuState(new Menu());
        try {
            this.soundManager = new SoundManager();
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public ColorSettings getColorSettings() {
        return colorSettings;
    }

    public static void main(String[] args) throws IOException, FontFormatException, URISyntaxException {
        new Game().start();
    }

    public void setState(State state) {
        this.state = state;
    }

    private void start() throws IOException {
        int frameTimeGame = 1000 / GameConfig.GAME_FPS;
        int frameTimeMENU = 1000 / GameConfig.MENU_FPS;

        int frameTime = 1000 / 30; // default
        while (this.state != null) {

            // change frames depending on current state to improve navigation in the menu
            if (state.getClass() == MenuState.class || state.getClass() == OptionsState.class || state.getClass() == EndState.class) frameTime = frameTimeMENU;
            else if (state.getClass() == GameState.class) frameTime = frameTimeGame;

            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
            soundManager.playBackground();
        }

        gui.close();
    }
}
