package com.aor.airhockey.gui;

import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

class LanternaGUITest {
    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics textGraphics;
    private ColorSettings colorSettings;

    @BeforeEach
    void setUp() throws IOException, java.net.URISyntaxException {
        screen = Mockito.mock(Screen.class);
        textGraphics = Mockito.mock(TextGraphics.class);
        colorSettings = Mockito.mock(ColorSettings.class);
        AWTTerminalFrame terminal = Mockito.mock(AWTTerminalFrame.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
        //Configurar cores padrão nos mocks para evitar NullPointerException
        Mockito.when(colorSettings.getBallColor()).thenReturn("#FFFFFF");
        Mockito.when(colorSettings.getWallColor()).thenReturn("#000000");
        Mockito.when(colorSettings.getDisk1Color()).thenReturn("#FF0000");
        Mockito.when(colorSettings.getDisk2Color()).thenReturn("#0000FF");
        gui = new LanternaGUI(screen, terminal, colorSettings);
    }

    @Test
    void drawSquare() {
        gui.drawSquare(5, 5, "#123456");
        Mockito.verify(textGraphics).setBackgroundColor(TextColor.Factory.fromString("#123456")); //se a cor foi definida
        Mockito.verify(textGraphics).putString(5, 6, " "); //posição correta
    }

    @Test
    void drawCircle() {
        Position center = new Position(10, 10);
        int radius = 2;
        String color = "#123456";
        gui.drawCircle(center, radius, color);
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString(color));
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).putString(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString());
        Mockito.verify(textGraphics, Mockito.never()).putString(15, 10, " ");//nao pode desenhar fora do sitio
    }

    @Test
    void drawBall() {
        Position pos = new Position(10.2, 20.8); // Testar arredondamento
        double radius = 1;
        gui.drawBall(pos, radius);
        Mockito.verify(colorSettings).getBallColor();//se pediu ao collor setings a cor da bola
        Mockito.verify(screen, Mockito.atLeastOnce()).newTextGraphics();//se pelo menos uma vez criou graficos
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).putString(Mockito.eq(10), Mockito.anyInt(), Mockito.eq(" "));
    }

    @Test
    void drawDiskPlayer1() {
        Position pos = new Position(5, 5);
        gui.drawDisk(pos, 2, 1);
        Mockito.verify(colorSettings).getDisk1Color();//se pediu ao collor setings a cor do disk1
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString("#FF0000"));//se pelo menos uma vez mudou a cor de fundo para a cor do player
    }

    @Test
    void drawDiskPlayer2() { //semehante ao de cima
        Position pos = new Position(5, 5);
        gui.drawDisk(pos, 2, 2);
        Mockito.verify(colorSettings).getDisk2Color();
        Mockito.verify(textGraphics, Mockito.atLeastOnce()).setBackgroundColor(TextColor.Factory.fromString("#0000FF"));
    }

    @Test
    void drawWall() {
        Position pos = new Position(2, 3);
        gui.drawWall(pos);
        Mockito.verify(colorSettings).getWallColor();
        Mockito.verify(textGraphics).putString(2, 4, " ");
    }

    @Test
    void clear() {
        gui.clear();
        Mockito.verify(screen).clear();
        Mockito.verify(textGraphics).fill(' ');
    }

    @Test
    void refresh() throws IOException {
        gui.refresh();
        Mockito.verify(screen).refresh();
    }

    @Test
    void close() throws IOException {
        gui.close();
        Mockito.verify(screen).close();
    }
}