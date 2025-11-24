package com.aor.airhockey.model.game.elements;

public class Ball extends Element {
    private int radius;

    public Ball(int x, int y, int r) {
        super(x, y);
        this.radius = r;
    }
}
