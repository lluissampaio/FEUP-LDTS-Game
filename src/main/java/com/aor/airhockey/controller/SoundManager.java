package com.aor.airhockey.controller;

import javax.sound.sampled.*;
import java.io.IOException;
import com.aor.airhockey.config.controller.SoundConfig;

public class SoundManager implements BackgroundMusic, MenuSounds, GameSounds{
    private final Sound menuScrollSound;
    private final Sound menuSelectSound;
    private final Sound backgroundMusic;
    private final Sound goalSound;

    public SoundManager() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        SoundLoader loader = new ClipSoundLoader();
        this.menuScrollSound = loader.load(SoundConfig.SCROLL_SOUND_PATH);
        this.menuSelectSound = loader.load(SoundConfig.SELECT_SOUND_PATH);
        this.backgroundMusic = loader.load(SoundConfig.BACKGROUND_SOUND_PATH);
        this.goalSound = loader.load(SoundConfig.GOAL_SOUND_PATH);
    }

    @Override
    public void playMenuSelect() {
        menuSelectSound.play();
    }

    @Override
    public void playMenuScroll() {
        menuScrollSound.play();
    }

    @Override
    public void playGoal() {
        goalSound.play();
    }

    @Override
    public void playBackground() {
        backgroundMusic.loop();
    }
}
