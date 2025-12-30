package com.aor.airhockey.model;

import com.aor.airhockey.model.menu.Menu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenuTest {
    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void initialization() {
        Assertions.assertTrue(menu.isSelected(0));
        Assertions.assertTrue(menu.isSelectedSingleplayer());
        Assertions.assertFalse(menu.isSelectedMultiplayer());
    }

    @Test
    void selectionDown() {
        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(1));
        Assertions.assertTrue(menu.isSelectedMultiplayer());

        menu.nextEntry();
        Assertions.assertTrue(menu.isSelected(2));
        Assertions.assertTrue(menu.isSelectedOptions());
    }

    @Test
    void selectionUp() {
        menu.previousEntry();
        Assertions.assertTrue(menu.isSelected(3));
        Assertions.assertTrue(menu.isSelectedExit());

        menu.previousEntry();
        Assertions.assertTrue(menu.isSelected(2));
    }

    @Test
    void selectionLoop() {
        int totalEntries = menu.getNumberEntries();

        for (int i = 0; i < totalEntries; i++) {
            menu.nextEntry();
        }

        Assertions.assertTrue(menu.isSelected(0));//voltar ao ínicio
    }
}
