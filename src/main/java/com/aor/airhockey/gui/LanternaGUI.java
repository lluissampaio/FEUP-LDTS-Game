package com.aor.airhockey.gui;

import com.aor.airhockey.config.gui.LanternaGUIConfig;
import com.aor.airhockey.config.gui.FontRendererConfig;
import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.Position;
import com.aor.airhockey.model.game.elements.Score;
import com.aor.airhockey.model.game.elements.Timer;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.graphics.TextImage;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
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
    private final ColorSettings colorSettings;
    private final FontRenderer fontRenderer;

    public LanternaGUI(Screen screen, Terminal terminal, ColorSettings colorSettings) throws IOException, URISyntaxException {
        this.screen = screen;
        this.terminal = (AWTTerminalFrame) terminal;
        this.colorSettings = colorSettings;
        fontRenderer = new FontRenderer();
    }

    public LanternaGUI(int width, int height, ColorSettings colorSettings) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        this.terminal = (AWTTerminalFrame) createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
        this.colorSettings = colorSettings;
        fontRenderer = new FontRenderer();

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
        URL resource = getClass().getClassLoader().getResource(LanternaGUIConfig.FONT_PATH);
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.BOLD, LanternaGUIConfig.FONT_SIZE);
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
            case KeyEvent.VK_UP -> ACTION.ARR_UP;
            case KeyEvent.VK_LEFT -> ACTION.ARR_LEFT;
            case KeyEvent.VK_DOWN -> ACTION.ARR_DOWN;
            case KeyEvent.VK_RIGHT -> ACTION.ARR_RIGHT;
            case KeyEvent.VK_Q -> ACTION.QUIT;
            case KeyEvent.VK_W -> ACTION.KEY_W;
            case KeyEvent.VK_A -> ACTION.KEY_A;
            case KeyEvent.VK_S -> ACTION.KEY_S;
            case KeyEvent.VK_D -> ACTION.KEY_D;
            case KeyEvent.VK_R -> ACTION.KEY_R;
            case KeyEvent.VK_ENTER -> ACTION.SELECT;
            default -> ACTION.NONE;
        };
    }

    public Set<ACTION> getNextActions() throws IOException {
        return new HashSet<>(actionSet); // we need to return of the current state of actionSet
    }

    @Override
    public void drawBall(Position position, double radius) {
        Position gridPosition = new Position(Math.round(position.getX()), Math.round(position.getY()));
        drawCircle(gridPosition, (int) radius, colorSettings.getBallColor());
    }

    @Override
    public void drawWall(Position position) {
        drawSquare((int) Math.round(position.getX()), (int) Math.round(position.getY()), colorSettings.getWallColor());
    }

    @Override
    public void drawDisk(Position position, double radius, int playerNumber) {
        String color = LanternaGUIConfig.DEFAULT_DISK_COLOR;
        if (playerNumber == 1) {
            color = colorSettings.getDisk1Color();
        }
        if (playerNumber == 2) {
            color = colorSettings.getDisk2Color();
        }
        Position gridPosition = new Position(Math.round(position.getX()), Math.round(position.getY()));
        drawCircle(gridPosition, (int) radius, color);
    }

    @Override
    public void drawHalfline(Position position) {
        drawSquare((int) Math.round(position.getX()), (int) Math.round(position.getY()), colorSettings.getWallColor());
    }

    @Override
    public void drawText(Position position, String text, String color) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            drawCharacter((int) Math.round(position.getX()) + i * FontRendererConfig.CHAR_WIDTH, (int) Math.round(position.getY()), c, color);
        }
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        BufferedImage charSprite = fontRenderer.getChar(c);
        for (int i = 0; i < charSprite.getWidth(); i++) {
            for (int j = 0; j < charSprite.getHeight(); j++) {
                int a = charSprite.getRGB(i, j);
                int red = (a >> 16) & 255;
                int green = (a >> 8) & 255;
                int blue = a & 255;

                if (red > 128 || green > 128 || blue > 128) {
                    TextColor foreground = TextColor.Factory.fromString(color);
                    TextCharacter pixel = new TextCharacter(' ', foreground, foreground);
                    tg.setCharacter(x + i, y + j, pixel);
                }
            }
        }
    }

    @Override
    public void drawSquare(int x, int y, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, " ");
    }

    @Override
    public void drawCircle(Position center, int radius, String color) {
        double degreesToRadians = Math.PI / 180.0;

        for (int r = 0; r <= radius; r++) {
            for (int degrees = 0; degrees < 360; degrees++) {
                double radians = degreesToRadians * degrees;
                int x = (int) Math.round(center.getX() + Math.cos(radians) * r);
                int y = (int) Math.round(center.getY() + Math.sin(radians) * r);
                drawSquare(x, y, color);
            }
        }
    }

    @Override
    public void drawScore(Score score, GUI gui) {
        gui.drawText(LanternaGUIConfig.SCORE_POSITION, String.format(LanternaGUIConfig.SCORE_STRING, score.getPlayer1Points(), score.getPlayer2Points()), LanternaGUIConfig.SCORE_COLOR);
    }

    @Override
    public void drawTimer(Timer timer, GUI gui)
    {
        if (timer.isOvertime()) {
            gui.drawText(LanternaGUIConfig.TIMER_POSITION, LanternaGUIConfig.OVERTIME_STRING, LanternaGUIConfig.OVERTIME_COLOR);
        } else {
            gui.drawText(LanternaGUIConfig.TIMER_POSITION, String.format(LanternaGUIConfig.TIMER_STRING,
                    timer.getRemainingMinutes(), timer.getDisplaySeconds()), LanternaGUIConfig.TIMER_COLOR);
        }
    }

    @Override
    public void clear() {
        screen.clear();
        TextGraphics tg = screen.newTextGraphics();
        tg.setBackgroundColor(TextColor.Factory.fromString(LanternaGUIConfig.BACKGROUND_COLOR));
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
