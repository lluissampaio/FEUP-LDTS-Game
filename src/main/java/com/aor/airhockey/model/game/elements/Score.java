package com.aor.airhockey.model.game.elements;

public class Score extends Element {
    private int player1Points;
    private int player2Points;

    // getters
    public int getPlayer1Points() { return this.player1Points; }
    public int getPlayer2Points() { return this.player2Points; }

    // score incrementer
    public void incrementPlayer1Points() { this.player1Points++;}
    public void incrementPlayer2Points() { this.player2Points++; }

    public Score(double x, double y) {
        super(x, y);

        this.player1Points = 0;
        this.player2Points = 0;
    }

    public Score(double x, double y, int p1_score, int p2_score)
    {
        super(36, 0);

        this.player1Points = p1_score;
        this.player2Points = p2_score;
    }
}
