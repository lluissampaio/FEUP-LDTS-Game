package com.aor.airhockey.viewer.menu;

import com.aor.airhockey.config.viewer.menu.EndViewerConfig;
import com.aor.airhockey.config.viewer.menu.MenuViewerConfig;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.End;
import com.aor.airhockey.viewer.Viewer;

public class EndViewer extends Viewer<End> {
    public EndViewer(End end) { super(end); }

    @Override
    protected void drawElements(GUI gui) {

        gui.drawText(new Position(EndViewerConfig.TITLE_X_POS, EndViewerConfig.TITLE_Y_POS), String.format(EndViewerConfig.TITLE_TEXT, getModel().getWinner()), EndViewerConfig.TITLE_COLOR);

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                new Position(EndViewerConfig.POSITIONS_LIST.get(i), EndViewerConfig.OPTIONS_Y_STARTING_POS + EndViewerConfig.OPTIONS_Y_DISTANCE * i),
                getModel().getEntry(i),
                getModel().isSelected(i) ? EndViewerConfig.IS_SELECTED_COLOR : EndViewerConfig.NOT_SELECTED_COLOR);
    }
}
