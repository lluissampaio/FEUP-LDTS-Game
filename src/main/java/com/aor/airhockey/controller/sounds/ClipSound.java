package com.aor.airhockey.controller.sounds;

import javax.sound.sampled.Clip;

public class ClipSound implements Sound {
    private final Clip clip;

    public ClipSound(Clip clip) {
        this.clip = clip;
    }

    @Override
    public void play() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }

    @Override
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
