package com.codecool.mightytextadventure;

import com.codecool.mightytextadventure.data.Area;
import com.codecool.mightytextadventure.logic.Game;
import com.codecool.mightytextadventure.ui.Display;
import com.codecool.mightytextadventure.ui.Input;
import com.codecool.mightytextadventure.data.Player;

import static com.codecool.mightytextadventure.data.Area.loadAreas;

public class Application {

    public static void main(String[] args) {
        Area[] areas = loadAreas();
        Player player = new Player("", areas[0]);
        Display display = new Display();
        Input input = new Input();

        Display.gameIntro(display, player, input);
        Game game = new Game(player, areas[0], areas[10], input, display);
        game.run();
    }
}
