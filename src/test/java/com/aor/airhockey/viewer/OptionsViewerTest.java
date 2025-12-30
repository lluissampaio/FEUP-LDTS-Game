package com.aor.airhockey.viewer;

import com.aor.airhockey.config.viewer.menu.OptionsViewerConfig;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.viewer.menu.OptionsViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.*;

class OptionsViewerTest {
    private OptionsViewer viewer;
    private Options options;
    private GUI gui;
    private ColorSettings colorSettings;

    @BeforeEach
    void setUp() {
        options = Mockito.mock(Options.class);
        gui = Mockito.mock(GUI.class);
        colorSettings = Mockito.mock(ColorSettings.class);
        Mockito.when(options.getColorSettings()).thenReturn(colorSettings);

        viewer = new OptionsViewer(options);
    }

    @Test
    void drawTitleAndEntries() {
        // Teste simples para texto
        Mockito.when(options.getNumberEntries()).thenReturn(1);
        Mockito.when(options.getEntry(0)).thenReturn("Ball Color");
        Mockito.when(options.isSelected(0)).thenReturn(true);
        Mockito.when(colorSettings.getBallColor()).thenReturn("#FFFFFF");

        viewer.drawElements(gui);

        Mockito.verify(gui).drawText(//para o titulo
                any(Position.class),
                eq(OptionsViewerConfig.OPTIONS_TITLE),
                eq(OptionsViewerConfig.OPTIONS_COLOR)
        );

        Mockito.verify(gui).drawText(//para o texto da opcao
                any(Position.class),
                eq("Ball Color"),
                eq(OptionsViewerConfig.IS_SELECTED_COLOR)
        );
    }

    @Test
    void drawColorSquares() {
        Mockito.when(options.getNumberEntries()).thenReturn(4);//simila 4 entradas

        Mockito.when(colorSettings.getBallColor()).thenReturn("#FF0000");   // Vermelho (index 0)
        Mockito.when(colorSettings.getDisk1Color()).thenReturn("#00FF00");  // Verde (index 1)
        Mockito.when(colorSettings.getDisk2Color()).thenReturn("#0000FF");  // Azul (index 2)
        Mockito.when(colorSettings.getWallColor()).thenReturn("#FFFF00");   // Amarelo (index 3)

        viewer.drawElements(gui);

        Mockito.verify(gui, Mockito.atLeastOnce()).drawSquare(
                anyInt(), anyInt(), eq("#FF0000")
        );

        Mockito.verify(gui, Mockito.atLeastOnce()).drawSquare(
                anyInt(), anyInt(), eq("#00FF00")
        );

        Mockito.verify(gui, Mockito.atLeastOnce()).drawSquare(
                anyInt(), anyInt(), eq("#0000FF")
        );

        Mockito.verify(gui, Mockito.atLeastOnce()).drawSquare(
                anyInt(), anyInt(), eq("#FFFF00")
        );
    }
}