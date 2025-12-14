package com.aor.airhockey.config.controller.game;

import com.aor.airhockey.model.Position;

public class BallControllerConfig {
    public static final double FRICTION_MULTIPLIER = -0.95;
    public static final double IMPACT_MULTIPLIER = 1.1;

    public static final Position MIDDLE_POSITION = new Position(150.0, 70.0);
    public static final double MAX_SPEED = 8.0;
}
