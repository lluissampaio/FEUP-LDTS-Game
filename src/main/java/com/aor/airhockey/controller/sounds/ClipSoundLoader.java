package com.aor.airhockey.controller.sounds;

import javax.sound.sampled.*;
import java.io.IOException;

public class ClipSoundLoader implements SoundLoader {
    @Override
    public Sound load(String resourcePath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(resourcePath));
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        return new ClipSound(clip);
    }
}
