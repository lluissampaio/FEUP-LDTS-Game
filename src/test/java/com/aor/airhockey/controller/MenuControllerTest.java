package com.aor.airhockey.controller;

import com.aor.airhockey.Game;
import com.aor.airhockey.controller.menu.MenuController;
import com.aor.airhockey.gui.GUI;
import com.aor.airhockey.model.ColorSettings;
import com.aor.airhockey.model.menu.Menu;
import com.aor.airhockey.states.GameState;
import com.aor.airhockey.states.OptionsState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;

public class MenuControllerTest {
    private MenuController controller;
    private Game game;
    private Menu menu;
    private StubInputHandler stubInputHandler;
    private StubMenuSounds stubMenuSounds;

    static class StubInputHandler implements InputHandler {
        private Set<GUI.ACTION> returnActions =  new HashSet<>();

        public void setReturnActions(GUI.ACTION... actions) {
            returnActions.clear();
            Collections.addAll(returnActions, actions);
        }

        @Override
        public Set<GUI.ACTION> processActions(Set<GUI.ACTION> actions) {
            return returnActions;
        }
    }

    static class StubMenuSounds implements MenuSounds {
        public boolean selectSoundPlayed = false;
        public boolean scrollSoundPlayed = false;

        @Override
        public void playMenuSelect(){
            selectSoundPlayed = true;
        }

        @Override
        public void playMenuScroll(){
            scrollSoundPlayed = true;
        }
    }

    @BeforeEach
    void setUp() {
        game = Mockito.mock(Game.class);
        menu = Mockito.mock(Menu.class);
        stubInputHandler = new StubInputHandler();
        stubMenuSounds = new StubMenuSounds();
        controller = new MenuController(menu, stubInputHandler, stubMenuSounds);
    }

    @Test
    void stepScrollUp() throws Exception{
        stubInputHandler.setReturnActions(GUI.ACTION.ARR_UP);

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(stubMenuSounds.scrollSoundPlayed);

        Mockito.verify(menu, Mockito.times(1)).previousEntry();
        Mockito.verify(game, Mockito.never()).setState(any());
    }

    @Test
    void stepScrollDown() throws Exception{
        stubInputHandler.setReturnActions(GUI.ACTION.ARR_DOWN);

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(stubMenuSounds.scrollSoundPlayed);

        Mockito.verify(menu, Mockito.times(1)).nextEntry();
        Mockito.verify(game, Mockito.never()).setState(any());
    }

    @Test
    void stepSelectMultiplayer() throws Exception{
        stubInputHandler.setReturnActions(GUI.ACTION.SELECT);
        Mockito.when(menu.isSelectedMultiplayer()).thenReturn(true);

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(stubMenuSounds.selectSoundPlayed);

        Mockito.verify(game, Mockito.times(1)).setState(any(GameState.class));
    }

    @Test
    void stepSelectOptions() throws Exception{
        stubInputHandler.setReturnActions(GUI.ACTION.SELECT);
        Mockito.when(menu.isSelectedOptions()).thenReturn(true);
        Mockito.when(game.getColorSettings()).thenReturn(Mockito.mock(ColorSettings.class));

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(stubMenuSounds.selectSoundPlayed);

        Mockito.verify(game, Mockito.times(1)).setState(any(OptionsState.class));
    }

    @Test
    void stepSelectExit() throws Exception{
        stubInputHandler.setReturnActions(GUI.ACTION.SELECT);
        Mockito.when(menu.isSelectedExit()).thenReturn(true);

        controller.step(game, Collections.emptySet(), 100);

        Assertions.assertTrue(stubMenuSounds.selectSoundPlayed);

        Mockito.verify(game, Mockito.times(1)).setState(null);
    }
}
