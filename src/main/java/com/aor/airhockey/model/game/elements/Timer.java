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

    public long getRemainingSeconds() {
        return (TimerConfig.GAME_DURATION - getElapsedTime()) / 1000 + 1;
    }

    public long getRemainingMinutes() {
        return getRemainingSeconds() / 60;
    }

    public long getDisplaySeconds() {
        return getRemainingSeconds() % 60;
    }

    public boolean isOvertime() {
        return getElapsedTime() >= TimerConfig.GAME_DURATION;
    }

}
