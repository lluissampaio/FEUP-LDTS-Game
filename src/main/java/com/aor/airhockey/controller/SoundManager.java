package com.aor.airhockey.controller;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundManager {
    private Clip menuScrollClip;
    private final Clip menuSelectClip;
    private Clip goalClip;
    private Clip backgroundClip;

    public SoundManager() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(SoundManager.class.getResource("/sounds/menuSelect.wav"));
        menuSelectClip = AudioSystem.getClip();
        menuSelectClip.open(audioStream1);
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(SoundManager.class.getResource("/sounds/menuScroll.wav"));
        menuScrollClip = AudioSystem.getClip();
        menuScrollClip.open(audioStream2);
        AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(SoundManager.class.getResource("/sounds/goal.wav"));
        goalClip = AudioSystem.getClip();
        goalClip.open(audioStream3);
        AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(SoundManager.class.getResource("/sounds/background.wav"));
        backgroundClip = AudioSystem.getClip();
        backgroundClip.open(audioStream4);
    }

    public void playMenuSelect() {
        if (menuSelectClip.isRunning()) {
            menuSelectClip.stop();
        }
        menuSelectClip.setFramePosition(0);
        menuSelectClip.start();
    }

    public void playMenuScroll() {
        if (menuScrollClip.isRunning()) {
            menuScrollClip.stop();
        }
        menuScrollClip.setFramePosition(0);
        menuScrollClip.start();
    }

    public void playGoal() {
        if (goalClip.isRunning()) {
            goalClip.stop();
        }
        goalClip.setFramePosition(0);
        goalClip.start();
    }

    public void playBackground() {
        backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
