package com.aor.airhockey.viewer.menu;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.viewer.Viewer;
import com.aor.airhockey.config.viewer.menu.OptionsViewerConfig;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class OptionsViewer extends Viewer<Options> {

    public OptionsViewer(Options options) {super(options);}

    @Override
    public void drawElements(GUI gui) {
        ColorSettings colorSettings = getModel().getColorSettings();
        gui.drawText(new Position(OptionsViewerConfig.OPTIONS_X_POS, OptionsViewerConfig.OPTIONS_Y_POS), OptionsViewerConfig.OPTIONS_TITLE, OptionsViewerConfig.OPTIONS_COLOR);


        for (int i = 0; i < getModel().getNumberEntries(); i++) {
            gui.drawText(
                    new Position(OptionsViewerConfig.POSITIONS_LIST.get(i), OptionsViewerConfig.OPTIONS_Y_STARTING_POS + OptionsViewerConfig.OPTIONS_Y_DISTANCE * i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? OptionsViewerConfig.IS_SELECTED_COLOR : OptionsViewerConfig.NOT_SELECTED_COLOR);

                if (i < 4) {
                String currentColor = switch(i) {
                    case 0 -> colorSettings.getBallColor();
                    case 1 -> colorSettings.getDisk1Color();
                    case 2 -> colorSettings.getDisk2Color();
                    case 3 -> colorSettings.getWallColor();
                    default -> OptionsViewerConfig.DEFAULT_COLOR;
                };
                gui.drawSquare(OptionsViewerConfig.SQUARE_X_POS, OptionsViewerConfig.SQUARE_Y_STARTING_POS + OptionsViewerConfig.SQUARE_Y_DISTANCE * i, currentColor);
            }
        }
    }
}