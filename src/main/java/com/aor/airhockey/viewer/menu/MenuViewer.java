package com.aor.airhockey.viewer.menu;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.config.viewer.menu.MenuViewerConfig;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(MenuViewerConfig.TITLE_X_POS, MenuViewerConfig.TITLE_Y_POS), MenuViewerConfig.TITLE_TEXT, MenuViewerConfig.TITLE_COLOR);

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(MenuViewerConfig.POSITIONS_LIST.get(i), MenuViewerConfig.OPTIONS_Y_STARTING_POS + MenuViewerConfig.OPTIONS_Y_DISTANCE * i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? MenuViewerConfig.IS_SELECTED_COLOR : MenuViewerConfig.NOT_SELECTED_COLOR);
    }
}
