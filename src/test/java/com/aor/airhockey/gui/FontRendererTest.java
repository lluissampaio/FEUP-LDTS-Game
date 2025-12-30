package com.aor.airhockey.gui;

import com.aor.airhockey.config.gui.FontRendererConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.lang.reflect.Field;

class FontRendererTest {

    @Test
    void getChar() throws Exception {
        FontRenderer fontRenderer = new FontRenderer();
        BufferedImage mockSpriteSheet = Mockito.mock(BufferedImage.class);
        BufferedImage mockCharImage = Mockito.mock(BufferedImage.class);

        Field field = FontRenderer.class.getDeclaredField("spriteSheet");
        field.setAccessible(true);
        field.set(fontRenderer, mockSpriteSheet);

        char character = 'A';
        int ascii = character;
        int expectedRow = ascii / FontRendererConfig.CHAR_PER_ROW;
        int expectedCol = ascii % FontRendererConfig.CHAR_PER_ROW;
        int expectedX = expectedCol * FontRendererConfig.CHAR_WIDTH;
        int expectedY = expectedRow * FontRendererConfig.CHAR_HEIGHT;

        Mockito.when(mockSpriteSheet.getSubimage(
                expectedX, expectedY,
                FontRendererConfig.CHAR_WIDTH, FontRendererConfig.CHAR_HEIGHT
        )).thenReturn(mockCharImage);

        BufferedImage result = fontRenderer.getChar(character);
        Assertions.assertEquals(mockCharImage, result);
        Mockito.verify(mockSpriteSheet).getSubimage(
                expectedX, expectedY,
                FontRendererConfig.CHAR_WIDTH, FontRendererConfig.CHAR_HEIGHT
        );
    }
}
