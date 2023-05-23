package com.codecool.mightytextadventure.ui;

import com.codecool.mightytextadventure.data.Player;

public class Display {
    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void clearConsole(){
        for(int i = 0; i < 100; i++)
            System.out.println();
    }

    public static void printSeparator(int n){
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    //printing out the most important information about the player character
    public static void characterInfo(Player player){
        clearConsole();
        printHeading("CHARACTER INFO");

        System.out.println(player.getName() + "\tHP: " + player.hp + "/" + player.maxHp);
        printSeparator(20);
        //player xp and gold
        System.out.println("XP: " + player.xp + "\tGold: " + player.gold);
        printSeparator(20);
        //# of pots
        System.out.println("# of Potions: " + player.pots);
        printSeparator(20);

        //printing the chosen traits
        if(player.numAtkUpgrades > 0){
            System.out.println("Offensive trait: " + player.atkUpgrades[player.numAtkUpgrades - 1]);
            printSeparator(20);
        }
        if(player.numDefUpgrades > 0){
            System.out.println("Defensive trait: " + player.defUpgrades[player.numDefUpgrades - 1]);
        }
        Input.anythingToContinue(player);
    }
    public static void gameIntro(Display display, Player player, Input input){
        boolean nameSet = false;
        display.clearConsole();
        display.printSeparator(40);
        display.printSeparator(30);
        display.printMessage(" Welcome to a world of magic, mystery, and excitement. A place where bravery and cunning are rewarded and the weak are left behind." +
                "\n You are a warrior on a quest to defeat the powerful evil sorcerer and claim the prize of ultimate power." +
                "\n Your journey will take you through treacherous landscapes, fierce battles, and heart-pounding encounters." +
                "\n But fear not, for with each victory you will acquire powerful artifacts that will help you in your quest." +
                "\n So sharpen your sword and ready your spells, for the road ahead will be long and arduous." +
                "\n The Sorcerer's Citadel awaits, and only the bravest and most cunning warrior will be able to defeat the evil sorcerer and claim the prize of ultimate power." +
                "\n Good luck, adventurer, and may your journey be filled with excitement and reward.");
        display.printSeparator(30);
        display.printSeparator(40);
//        display.printMessage("Starting Mighty Text Adventure!");
        do{
            printHeading("What's your name?");
            player.setName(Input.getStringFromUser());
            //asking the player if he wants to correct his choice
            clearConsole();
            printHeading("Your name is " + player.getName() + ".\nIs that correct?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int choice = input.readInt("-> ", 2);
            if(choice == 1)
                nameSet = true;
        }while(!nameSet);
        //let the player choose a trait when creating a new character
        player.chooseTrait(player);
    }

    public static void printEnd(Player player){
        clearConsole();
        printSeparator(30);
        printMessage("""
                 As you strike the final blow against the evil sorcerer, a brilliant flash of light illuminates the room.
                The citadel trembles and begins to crumble, but you make a break for the exit, dodging falling debris as you go.

                 Finally, you burst out into the sunlight, gasping for breath. You turn and look back at the collapsing citadel,
                feeling a sense of triumph wash over you. You have saved the land from darkness, and brought peace to its inhabitants once again.

                 As you walk away, the townsfolk begin to gather around you, cheering and clapping you on the back.
                They shower you with gifts and praise, and a great feast is held in your honor. You sit back and enjoy the festivities,
                basking in the knowledge that you are a true hero.

                 Years pass, and the tales of your bravery become legend. Children listen in awe as they hear of your great adventure,
                and the people of the land sing your praises. You may never be able to fully comprehend the impact you have had on their lives,
                but know that you will forever be remembered as a champion of good, and a true hero of the realm.""");
        printSeparator(30);
        System.out.println("This game was developed by CodeCrafters");
        System.out.println("We hope you enjoyed it!");
    }
}