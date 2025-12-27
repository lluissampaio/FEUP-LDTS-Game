package com.aor.airhockey.controller;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public interface SoundLoader {
    Sound load(String resourcePath) throws LineUnavailableException, IOException, UnsupportedAudioFileException;
}
