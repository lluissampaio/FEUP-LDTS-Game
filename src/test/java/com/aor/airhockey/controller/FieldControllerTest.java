package com.aor.airhockey.controller;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.game.FieldController;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.Velocity;
import com.aor.airhockey.model.game.elements.Ball;
import com.aor.airhockey.model.game.elements.Disk;
import com.aor.airhockey.model.game.elements.Score;
import com.aor.airhockey.model.game.field.Field;
import com.aor.airhockey.states.EndState;
import com.aor.airhockey.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

class FieldControllerTest {
    private FieldController controller;
    private Field field;
    private Game game;

    @BeforeEach
    void setUp() throws Exception {
        field = Mockito.mock(Field.class);
        game = Mockito.mock(Game.class);

        Disk disk1 = Mockito.mock(Disk.class);
        Disk disk2 = Mockito.mock(Disk.class);
        Ball ball = Mockito.mock(Ball.class);
        Score score = Mockito.mock(Score.class);
        // Configurar Disco 1
        Mockito.when(disk1.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(disk1.getVelocity()).thenReturn(new Velocity(0, 0));
        Mockito.when(disk1.getRadius()).thenReturn(1.0);
        // Configurar Disco 2
        Mockito.when(disk2.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(disk2.getVelocity()).thenReturn(new Velocity(0, 0));
        Mockito.when(disk2.getRadius()).thenReturn(1.0);
        // Configurar Bola
        Mockito.when(ball.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(ball.getVelocity()).thenReturn(new Velocity(0, 0));
        Mockito.when(ball.getRadius()).thenReturn(1.0);

        Mockito.when(field.getDisk1()).thenReturn(disk1);
        Mockito.when(field.getDisk2()).thenReturn(disk2);
        Mockito.when(field.getBall()).thenReturn(ball);
        Mockito.when(field.getScore()).thenReturn(score);
        Mockito.when(field.getWalls()).thenReturn(Collections.emptyList());
        Mockito.when(field.getGoals()).thenReturn(Collections.emptyList());
        Mockito.when(field.getHalfline()).thenReturn(Collections.emptyList());

        controller = new FieldController(field);
    }

    @Test
    void stepQuit() throws Exception { //ultilizador quer sair
        Set<GUI.ACTION> actions = Set.of(GUI.ACTION.QUIT);
        controller.step(game, actions, 100);
        Mockito.verify(game, Mockito.times(1)).setState(any(MenuState.class));//o jogo deve mudar para o estado de Menu
    }

    @Test
    void stepWinner() throws Exception {
        Mockito.when(field.getWinner()).thenReturn(1);//significa que o jogo acabou com o vencedor
        controller.step(game, Collections.emptySet(), 100);
        Mockito.verify(game, Mockito.times(1)).setState(any(EndState.class));//o jogo deve mudar para o estado de End
    }

    @Test
    void stepContinues() throws Exception {
        Mockito.when(field.getWinner()).thenReturn(0); //significa sem vencedor
        controller.step(game, Collections.emptySet(), 100);
        Mockito.verify(game, Mockito.never()).setState(any());//o estado nao deve mudar
    }
}
