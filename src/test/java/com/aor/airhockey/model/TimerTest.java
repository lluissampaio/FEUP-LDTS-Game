package com.aor.airhockey.model;

import com.aor.airhockey.model.game.elements.Timer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimerTest {

    @Test
    void constructor() {
        Timer timer = new Timer(100, 200);
        Assertions.assertEquals(100, timer.getPosition().getX());
        Assertions.assertEquals(200, timer.getPosition().getY());
    }

    @Test
    void timeFlows() throws InterruptedException {
        Timer timer = new Timer(0, 0);
        long inicio = timer.getElapsedTime();
        Thread.sleep(100);//Forçamos o computador a esperar 100 milissegundos
        long fim = timer.getElapsedTime();

        Assertions.assertTrue(fim >= inicio + 100);
    }

    @Test
    void minutesAndSecondsLogic() {
        Timer timer = new Timer(0, 0);

        long totalSeconds = timer.getRemainingSeconds();
        long minutes = timer.getRemainingMinutes();
        long displaySeconds = timer.getDisplaySeconds();

        Assertions.assertEquals(minutes, totalSeconds / 60);//se os minutos batem certo
        Assertions.assertEquals(displaySeconds, totalSeconds % 60);//se os segundos batem  certo
    }

}
