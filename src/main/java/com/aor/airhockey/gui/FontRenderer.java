package com.aor.airhockey.gui;

import com.aor.airhockey.config.gui.FontRendererConfig;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FontRenderer {
    private final BufferedImage spriteSheet;

    public FontRenderer() throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(FontRendererConfig.FONT_PATH);
        File fontFile = new File(resource.toURI());
        spriteSheet = ImageIO.read(fontFile);
    }

    public BufferedImage getChar(char c) {
        int ascii = c;

        int row = ascii / FontRendererConfig.CHAR_PER_ROW;
        int col = ascii % FontRendererConfig.CHAR_PER_ROW;

        int x = col * FontRendererConfig.CHAR_WIDTH;
        int y = row * FontRendererConfig.CHAR_HEIGHT;

        return spriteSheet.getSubimage(x, y, FontRendererConfig.CHAR_WIDTH, FontRendererConfig.CHAR_HEIGHT);
    }
}
