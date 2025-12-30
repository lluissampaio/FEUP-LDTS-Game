package com.aor.airhockey.viewer;

import com.aor.airhockey.config.viewer.menu.MenuViewerConfig;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.menu.MenuViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MenuViewerTest {
    private MenuViewer viewer;
    private Menu menu;
    private GUI gui;

    @BeforeEach
    void setUp() {
        menu = Mockito.mock(Menu.class);
        gui = Mockito.mock(GUI.class);
        viewer = new MenuViewer(menu);
    }

    @Test
    void drawElements() {
        Mockito.when(menu.getNumberEntries()).thenReturn(2);//menu com duas opções

        Mockito.when(menu.getEntry(0)).thenReturn("Singleplayer");
        Mockito.when(menu.isSelected(0)).thenReturn(true);//selecionada

        Mockito.when(menu.getEntry(1)).thenReturn("Exit");
        Mockito.when(menu.isSelected(1)).thenReturn(false);//nao selecionada

        viewer.drawElements(gui);

        Mockito.verify(gui, Mockito.times(1)).drawText(
                Mockito.any(Position.class),
                Mockito.eq(MenuViewerConfig.TITLE_TEXT),
                Mockito.eq(MenuViewerConfig.TITLE_COLOR));

        Mockito.verify(gui, Mockito.times(1)).drawText(
                Mockito.any(Position.class),
                Mockito.eq("Singleplayer"),
                Mockito.eq(MenuViewerConfig.IS_SELECTED_COLOR)); // Cor de destaque

        Mockito.verify(gui, Mockito.times(1)).drawText(
                Mockito.any(Position.class),
                Mockito.eq("Exit"),
                Mockito.eq(MenuViewerConfig.NOT_SELECTED_COLOR)); // Cor normal
    }
}
