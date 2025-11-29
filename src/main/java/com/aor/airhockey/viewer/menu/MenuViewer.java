package com.aor.airhockey.viewer.menu;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.viewer.Viewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawText(new Position(33, 7), "Air Hockey", "#018ABE");

        List<Integer> positions = Arrays.asList(32, 32, 34, 36);

        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(positions.get(i), 14 + 3*i),
                    getModel().getEntry(i),
                    getModel().isSelected(i) ? "#FF3333" : "#FFFFFF");
    }
}
