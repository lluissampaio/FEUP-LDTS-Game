package com.aor.airhockey.viewer.game;

import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.game.elements.Disk;

public class DiskViewer implements ElementViewer<Disk> {
    @Override
    public void draw(Disk disk, GUI gui) {gui.drawDisk(disk.getPosition(), disk.getPlayerNumber());}
}