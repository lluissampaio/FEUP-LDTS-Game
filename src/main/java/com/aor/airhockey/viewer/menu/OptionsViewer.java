package com.aor.airhockey.viewer.menu;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.Options;
import com.aor.airhockey.viewer.Viewer;

import java.util.Arrays;
import java.util.List;

public class OptionsViewer extends Viewer<Options> {
    public OptionsViewer(Options options) {
        super(options);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(34, 7), "Options", "#018ABE");

        List<Integer> positions = Arrays.asList(33, 33, 33, 36);

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(positions.get(i), 14 + 3*i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FF3333" : "#FFFFFF");
    }
}