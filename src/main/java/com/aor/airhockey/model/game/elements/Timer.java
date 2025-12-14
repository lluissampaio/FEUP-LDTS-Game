package com.aor.airhockey.model.game.elements;

import com.aor.airhockey.config.model.game.elements.TimerConfig;

public class Timer extends Element {
    private final long startTime;

    public Timer(double x, double y) {
        super(x, y);

        startTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public long getElapsedSeconds() {
        // returns seconds since the beginning of the game
        return ((System.currentTimeMillis() - startTime) / 1000) - (getElapsedMinutes() * 60);
    }

    public long getElapsedMinutes() {
        // return minutes since the beginning of the game
        return (System.currentTimeMillis() - startTime) / 1000 / 60;
    }

    public boolean isOvertime() {
        return getElapsedTime() >= TimerConfig.GAME_DURATION;
    }

}
