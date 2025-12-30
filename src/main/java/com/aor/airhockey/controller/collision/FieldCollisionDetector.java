package com.aor.airhockey.controller.collision;

import com.aor.airhockey.model.Position;

public interface FieldCollisionDetector {
    CollisionAxis collisionAxis(Position position, double radius);
}
