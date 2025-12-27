package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Goal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GoalTest {

    @Test
    public void createGoal() {
        Goal goal = new Goal(10, 20);
        Assertions.assertEquals(10, goal.getPosition().getX());
        Assertions.assertEquals(20, goal.getPosition().getY());
    }
}
