package com.codecool.mightytextadventure.logic;

import com.codecool.mightytextadventure.data.Action;
import com.codecool.mightytextadventure.data.Area;
import com.codecool.mightytextadventure.data.Enemy;
import com.codecool.mightytextadventure.data.Player;
import com.codecool.mightytextadventure.ui.Display;
import com.codecool.mightytextadventure.ui.Input;

public class Game {
    private static Player player;
    private final Area startingArea;
    private final Area goalArea;
    private final Input input;
    private final Display display;

    public Game(Player player, Area startingArea, Area goalArea, Input input, Display display) {
        this.player = player;
        this.startingArea = startingArea;
        this.goalArea = goalArea;
        this.input = input;
        this.display = display;
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            isRunning = step();
        }
    }

    private boolean step() {
        display.clearConsole();
        display.printMessage(player.currentArea.getName() + " -- " + player.currentArea.getDescription());
        input.anythingToContinue(player);

        if (player.currentArea.getName().equals("The Healing Haven")) {
            shop();
        } else if (player.currentArea.getName().equals("The Sorcerer's Citadel")) {
            finalBattle();
        } else if (!player.currentArea.getName().equals("The Adventurer's Guild")) {
            randomBattle();
        }

        checkXP();

        Action[] actions = player.currentArea.getActions();
        for(int i = 0; i < actions.length; i++){
            display.printMessage(String.format("%d. %s", i + 1, actions[i].getDescription()));
        }

        int selectedAction = input.readInt("->",actions.length);
        Action action = actions[selectedAction - 1];

        if(action.getDescription().equals("Character Info")){
            Display.characterInfo(player);
        }else{
            player.currentArea = action.getTargetArea();
        }

        if(player.currentArea == goalArea) {
            return false;
        }
        return true;
    }

    //shopping / encountering a travelling trader
    public static void shop(){
        Display.clearConsole();
        Display.printHeading("You meet a mysterious stranger.\nHe offers you something:");
        int price = (int) (Math.random()*(10 + player.pots*3) + 10 + player.pots);
        System.out.println("- Magic Potion: " + price + " gold.");
        Display.printSeparator(20);
        //ask the player to buy one
        System.out.println("Do you want to buy one?\n(1) Yes!\n(2) No thanks.");
        int input = Input.readInt("-> ", 2);
        //check if player wants to buy
        if(input == 1){
            Display.clearConsole();
            //check if player has enough gold
            if(player.gold >= price){
                Display.printHeading("You bought a magical potion for " + price + "gold.");
                player.pots++;
                player.gold -= price;
            }else{
                Display.printHeading("You don't have enough gold to buy this...");
            }
            Input.anythingToContinue(player);
        }
    }

    //creating a random battle
    public static void randomBattle(){
        Display.clearConsole();
        Display.printHeading("You encountered an evil winded creature. You'll have to fight it!");
        Input.anythingToContinue(player);
        //creating new enemy with random name
        battle(new Enemy(Enemy.enemies[(int)(Math.random()* Enemy.enemies.length)], player.xp));
    }

    //the main BATTLE method
    public static void battle(Enemy enemy){
        //main battle loop
        while(true){
            Display.clearConsole();
            Display.printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHp);
            Display.printHeading(player.getName() + "\nHP: " + player.hp + "/" + player.maxHp);
            System.out.println("Choose an action:");
            Display.printSeparator(20);
            System.out.println("(1) Fight\n(2) Use Potion\n(3) Run Away!");
            int input = Input.readInt("-> ", 3);
            //react accordingly to player input
            if(input == 1){
                //FIGHT
                //calculate dmg and dmgTook (dmg enemy deals to player)
                int dmg = player.attack() - enemy.defend();
                int dmgTook = enemy.attack() - player.defend();
                //check that dmg and dmgTook isn't negative
                if(dmgTook <= 0) {
                    //add some dmg if player defends very well
                    dmg -= dmgTook/2;
                    dmgTook = 0;
                }
                if(dmg < 0){
                    dmg = 0;
                }
                //deal damage to both parties
                player.hp -= dmgTook;
                enemy.hp -= dmg;
                //print the info of this battle round
                Display.clearConsole();
                Display.printHeading("BATTLE");
                System.out.println("You dealt " + dmg + " damage to the " + enemy.name + ".");
                Display.printSeparator(15);
                System.out.println("The " + enemy.name + " dealt " + dmgTook + " damage to you.");
                Input.anythingToContinue(player);
                //check if player is still alive or dead
                if(player.hp <= 0){
                    playerDied(); //method to end the game
                    break;
                } else if (enemy.hp <= 0) {
                    if(enemy.name.equals("THE EVIL WIZARD")){
                        Display.printEnd(player);
                    }
                    //tell the player he won
                    Display.clearConsole();
                    Display.printHeading("You defeated the " + enemy.name + "!");
                    //increase player xp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + " XP!");
                    //random drops
                    int goldEarned = (int)(Math.random()*enemy.xp);
                    if(goldEarned > 0){
                        player.gold += goldEarned;
                        System.out.println("You collect " + goldEarned + " gold from the " + enemy.name + "'s corpse!");
                    }
                    Input.anythingToContinue(player);
                    break;
                }
            } else if (input == 2) {
                //use potion
                Display.clearConsole();
                if(player.pots > 0 && player.hp < player.maxHp){
                    //player CAN take a potion
                    //make sure player want to drink a potion
                    Display.printHeading("Do you want to drink a potion? (" + player.pots + " left).");

                }else{
                    //player CANNOT take a potion
                    Display.printHeading("You don't have any potions or you're at full health.");
                    Input.anythingToContinue(player);
                    System.out.println("(1) Yes\n(2) No, maybe later");
                    input = Input.readInt("-> ", 2);
                    if(input == 1) {
                        //player actually took it
                        player.hp = player.maxHp;
                        Display.clearConsole();
                        Display.printHeading("You drank a magic potion. It restored your health back to " + player.maxHp);
                        Input.anythingToContinue(player);
                    }
                }
            }else{
                //RUN AWAY
                Display.clearConsole();
                if(!player.currentArea.getName().equals("The Sorcerer's Citadel")) {
                    //chance of 35% to escape
                    if(Math.random()*10 + 1 <= 3.5){
                        Display.printHeading("You ran away from the " + enemy.name + "!");
                        Input.anythingToContinue(player);
                        break;
                    }else{
                        Display.printHeading("You didn't manage to escape.");
                        //calculate damage the player takes
                        int dmgTook = enemy.attack();
                        System.out.println("In your hurry you took " + dmgTook + " damage!");
                        Input.anythingToContinue(player);
                        //check if player is still alive
                        if(player.hp <= 0) {
                            playerDied();
                        }
                    }
                }else{
                    Display.printHeading("YOU CANNOT ESCAPE THE EVIL WIZARD!!!");
                    Input.anythingToContinue(player);
                }
            }
        }
    }
    //the final battle of the entire game
    public static boolean finalBattle(){
        //creating the evil emperor and letting the player fight against him
        battle(new Enemy("THE EVIL WIZARD", player.xp + 10));
        //printing the proper ending
        return false;
    }

    //method that gets called when the player is dead
    public static boolean playerDied(){
        Display.clearConsole();
        Display.printHeading("You died...");
        Display.printHeading("You earned " + player.xp + " XP on your journey. Try to earn more next time!");
        System.out.println("Thank you for playing. Hope you enjoyed it :)");
        return false;
    }

    //method that changes the game's values based on player xp
    public static void checkXP(){
        if(player.xp >= 10 && (player.numAtkUpgrades + player.numDefUpgrades) == 1){
            //let player "level up"
            player.chooseTrait(player);
        } else if (player.xp >= 50 && (player.numAtkUpgrades + player.numDefUpgrades) == 2)  {
            //lvl up
            player.chooseTrait(player);
        } else if (player.xp >= 100 && (player.numAtkUpgrades + player.numDefUpgrades) == 3) {
            //lvl up
            player.chooseTrait(player);
        }
    }
}
