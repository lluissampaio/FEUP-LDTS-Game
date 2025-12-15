package com.aor.airhockey.config.controller.game;

import com.aor.airhockey.config.ConfigCalculator;
import com.aor.airhockey.model.Position;

public class BallControllerConfig {
    public static final double FRICTION_MULTIPLIER = -0.80;
    public static final double IMPACT_MULTIPLIER = 1.1;

    public static final Position MIDDLE_POSITION = new Position(ConfigCalculator.calculateCenterX(), ConfigCalculator.calculateCenterY());
    public static final double MAX_SPEED = 6.0;
}
