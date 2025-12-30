package com.aor.airhockey.model;

import com.aor.airhockey.model.menu.End;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    void constructor() {
        End end = new End(1, 5, 3);

        Assertions.assertEquals(1, end.getWinner());
        Assertions.assertEquals(5, end.getP1_score());
        Assertions.assertEquals(3, end.getP2_score());
    }

    @Test
    void selection() {
        End end = new End(1, 0, 0);

        Assertions.assertTrue(end.isSelected(0));
        Assertions.assertTrue(end.isSelectedPlayAgain());

        end.nextEntry();
        Assertions.assertTrue(end.isSelected(1));
        Assertions.assertTrue(end.isSelectedGoToMenu());

        end.nextEntry();
        Assertions.assertTrue(end.isSelected(2));
        Assertions.assertTrue(end.isSelectedExit());

        end.nextEntry();
        Assertions.assertTrue(end.isSelected(0));//vai do ultimo para o primeiro
    }

    @Test
    void reverseSelection() {
        End end = new End(2, 2, 4);

        end.previousEntry();
        Assertions.assertTrue(end.isSelectedExit());//do primeiro para o ultimo
    }
}