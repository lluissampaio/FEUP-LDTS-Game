package com.aor.airhockey.viewer;

import com.aor.airhockey.config.viewer.menu.EndViewerConfig;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.End;
import com.aor.airhockey.viewer.menu.EndViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

class EndViewerTest {
    private EndViewer viewer;
    private End endModel;
    private GUI gui;

    @BeforeEach
    void setUp() {
        endModel = Mockito.mock(End.class);
        gui = Mockito.mock(GUI.class);
        viewer = new EndViewer(endModel);
    }

    @Test
    void drawElements() {
        Mockito.when(endModel.getWinner()).thenReturn(2); // Ganhou o Player 2
        Mockito.when(endModel.getP1_score()).thenReturn(3);
        Mockito.when(endModel.getP2_score()).thenReturn(5);

        Mockito.when(endModel.getNumberEntries()).thenReturn(2);//duas opções de menu
        Mockito.when(endModel.getEntry(0)).thenReturn("Play Again");
        Mockito.when(endModel.isSelected(0)).thenReturn(true); // Selecionado

        Mockito.when(endModel.getEntry(1)).thenReturn("Exit");
        Mockito.when(endModel.isSelected(1)).thenReturn(false); // Não Selecionado

        viewer.drawElements(gui);

        String expectedTitle = String.format(EndViewerConfig.TITLE_TEXT, 2);
        Mockito.verify(gui).drawText(
                any(Position.class),
                eq(expectedTitle),
                eq(EndViewerConfig.TITLE_COLOR)
        );

        String expectedScore = String.format(EndViewerConfig.FINAL_SCORE, 3, 5);
        Mockito.verify(gui).drawText(
                any(Position.class),
                eq(expectedScore),
                eq(EndViewerConfig.SCORE_COLOR)
        );

        Mockito.verify(gui).drawText(
                any(Position.class),
                eq("Play Again"),
                eq(EndViewerConfig.IS_SELECTED_COLOR)
        );

        Mockito.verify(gui).drawText(
                any(Position.class),
                eq("Exit"),
                eq(EndViewerConfig.NOT_SELECTED_COLOR)
        );
    }
}
