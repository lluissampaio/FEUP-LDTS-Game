package com.aor.airhockey.controller;

import com.aor.airhockey.model.Position;

public interface FieldCollisionDetector {
    CollisionAxis collisionAxis(Position position, double radius);
}
