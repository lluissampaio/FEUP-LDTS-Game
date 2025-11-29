package com.aor.airhockey.gui;

import com.aor.airhockey.model.Position;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class LanternaGUI implements GUI {
    private final Screen screen;
    private final AWTTerminalFrame terminal;
    private final Set<ACTION> actionSet = new HashSet<>();
    private final String backgroundColor = "#0F172A";

    public LanternaGUI(Screen screen, Terminal terminal) {
        this.screen = screen;
        this.terminal = (AWTTerminalFrame) terminal;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        this.terminal = (AWTTerminalFrame) createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);

        startKeyListeners();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    private void startKeyListeners() {

        ((AWTTerminalFrame) terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                addKeyEvent(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                removeKeyEvent(e);
            }
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//                handleKeyTyped(e);
//            }
        });
    }

    private void addKeyEvent(KeyEvent e) {
        ACTION action = keyEventToAction(e);
        if (action != ACTION.NONE) actionSet.add(action);
    }

    private void removeKeyEvent(KeyEvent e) {
        ACTION action = keyEventToAction(e);
        if (action != ACTION.NONE) actionSet.remove(action);
    }

    private ACTION keyEventToAction(KeyEvent e) {
        int keycode = e.getKeyCode();

        return switch (keycode) {
            case KeyEvent.VK_UP -> ACTION.ARR_N;
            case KeyEvent.VK_LEFT -> ACTION.ARR_W;
            case KeyEvent.VK_DOWN -> ACTION.ARR_S;
            case KeyEvent.VK_RIGHT -> ACTION.ARR_E;
            case KeyEvent.VK_Q -> ACTION.QUIT;
            case KeyEvent.VK_W -> ACTION.KEY_N;
            case KeyEvent.VK_A -> ACTION.KEY_W;
            case KeyEvent.VK_S -> ACTION.KEY_S;
            case KeyEvent.VK_D -> ACTION.KEY_E;
            case KeyEvent.VK_ENTER -> ACTION.SELECT;
            default -> ACTION.NONE;
        };
    }

    public Set<ACTION> getNextActions() throws IOException {
        return new HashSet<>(actionSet); // we need to return of the current state of actionSet
    }

    @Override
    public void drawBall(Position position) {
        drawCharacter(position.getX(), position.getY(), 'B', "#FFFFFF");
    }

    @Override
    public void drawWall(Position position) {
        drawCharacter(position.getX(), position.getY(), '#', "#3333FF");
    }

    @Override
    public void drawDisk(Position position) { drawCharacter(position.getX(), position.getY(), 'D', "#FF0000");}

    @Override
    public void drawHalfline(Position position) {drawCharacter(position.getX(), position.getY(), 'L', "#808080");}

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        tg.putString(position.getX(), position.getY(), text);
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        tg.putString(x, y + 1, "" + c);
    }

    @Override
    public void clear() {
        screen.clear();
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(this.backgroundColor));
        tg.fill(' ');
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
