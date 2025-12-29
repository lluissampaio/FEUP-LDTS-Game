package com.aor.airhockey.controller;

import com.aor.airhockey.model.Position;

public interface CircleCollisionDetector {
    CollisionInfo collisionInfo(Position position1, double radius1, Position position2, double radius2);
}
