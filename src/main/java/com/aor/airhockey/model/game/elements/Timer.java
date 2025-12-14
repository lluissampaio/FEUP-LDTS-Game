package com.aor.airhockey.model.game.elements;

import com.aor.airhockey.config.model.game.elements.TimerConfig;

public class Timer extends Element {
    private final long startTime;

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }

    public boolean isOvertime() {
        return getElapsedTime() >= TimerConfig.GAME_DURATION;
    }

    public Timer(double x, double y) {
        super(x, y);

        startTime = System.currentTimeMillis();
    }
}
