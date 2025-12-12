package com.aor.airhockey.model.game.field;

import com.aor.airhockey.model.game.elements.*;
import com.aor.airhockey.config.model.game.field.LoaderFieldBuilderConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoaderFieldBuilder extends FieldBuilder {
    private final List<String> lines;

    public LoaderFieldBuilder() throws IOException {
        URL resource = LoaderFieldBuilder.class.getResource(LoaderFieldBuilderConfig.FIELD_RESOURCE_PATH);
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        lines = readLines(br);
    }

    private List<String> readLines(BufferedReader br) throws IOException {
        List<String> lines = new ArrayList<>();
        for (String line; (line = br.readLine()) != null; )
            lines.add(line);
        return lines;
    }

    @Override
    protected int getWidth() {
        int width = 0;
        for (String line : lines)
            width = Math.max(width, line.length());
        return width;
    }

    @Override
    protected int getHeight() {
        return lines.size();
    }

    @Override
    protected List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == LoaderFieldBuilderConfig.WALL_CHARACTER) walls.add(new Wall(x, y));
        }

        return walls;
    }

    @Override
    protected Disk createDisk(int diskNumber) {
        int count = 1;
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                if (line.charAt(x) == LoaderFieldBuilderConfig.DISK_CHARACTER) {
                    if (count == diskNumber) {
                        return new Disk(x, y, 2, count);
                    }
                    count++;
                }
            }
        }

        return null;
    }

    @Override
    protected Ball createBall() {
        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == LoaderFieldBuilderConfig.BALL_CHARACTER) return new Ball(x, y, 1);
        }
        return null;
    }

    @Override
    protected List<Halfline> createHalfline() {
        List<Halfline> halfline = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == LoaderFieldBuilderConfig.HALFLINE_CHARACTER) halfline.add(new Halfline(x, y));
        }

        return halfline;
    }

    @Override
    protected List<Goal> createGoals() {
        List<Goal> goals = new ArrayList<>();

        for (int y = 0; y < lines.size(); y++) {
            String line = lines.get(y);
            for (int x = 0; x < line.length(); x++)
                if (line.charAt(x) == LoaderFieldBuilderConfig.GOAL_CHARACTER) goals.add(new Goal(x, y));
        }

        return goals;
    }
}
