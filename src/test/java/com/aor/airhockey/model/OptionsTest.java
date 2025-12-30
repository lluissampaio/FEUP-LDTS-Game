package com.aor.airhockey.model;

import com.aor.airhockey.model.menu.Options;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyString;

class OptionsTest {
    private Options options;
    private ColorSettings colorSettingsMock;

    @BeforeEach
    void setUp() {
        colorSettingsMock = Mockito.mock(ColorSettings.class);

        Mockito.when(colorSettingsMock.getBallColor()).thenReturn("RED");
        Mockito.when(colorSettingsMock.getDisk1Color()).thenReturn("BLUE");
        Mockito.when(colorSettingsMock.getDisk2Color()).thenReturn("GREEN");
        Mockito.when(colorSettingsMock.getWallColor()).thenReturn("WHITE");

        options = new Options(colorSettingsMock);
    }

    @Test
    void selectionVertical() {
        Assertions.assertTrue(options.isSelectedBallColor()); // 0

        options.nextEntry();
        Assertions.assertTrue(options.isSelectedDisk1Color()); // 1

        options.nextEntry();
        Assertions.assertTrue(options.isSelectedDisk2Color()); // 2

        options.previousEntry();
        Assertions.assertTrue(options.isSelectedDisk1Color()); // Volta ao 1
    }

    @Test
    void changeBallColor() {
        options.nextColor();

        Mockito.verify(colorSettingsMock, Mockito.times(1)).setBallColor(anyString());
        Mockito.verify(colorSettingsMock, Mockito.never()).setDisk1Color(anyString());
    }

    @Test
    void changeWallColor() {
        while(!options.isSelectedWallColor()) {//ir até ao Wall
            options.nextEntry();
        }
        Assertions.assertTrue(options.isSelectedWallColor());

        options.previousColor();
        Mockito.verify(colorSettingsMock, Mockito.times(1)).setWallColor(anyString());
    }

}
