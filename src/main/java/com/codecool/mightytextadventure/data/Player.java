package com.codecool.mightytextadventure.data;

import com.codecool.mightytextadventure.ui.Display;
import com.codecool.mightytextadventure.ui.Input;

import static com.codecool.mightytextadventure.data.Area.loadAreas;

public class Player extends Character{
    public Area currentArea;
    private String name;

    //integers to store number of upgrades/skills in each path
    public  int numAtkUpgrades, numDefUpgrades;

    //additional player stats
    public int gold;
    int restsLeft;
    public int pots;

    public String[] atkUpgrades = {"Strength", "Power", "Knight", "Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones", "Stoneskin", "Scale Armor", "Holy Aura"};

    public Player(String name, Area currentArea) {
        //calling constructor of superclass
        super(name, 100, 0);
        //Setting # of upgrades to 0
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        //set additional stats
        this.gold = 3;
        this.restsLeft = 1;
        this.pots = 0;
        this.currentArea = currentArea;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public int attack() {
        int baseAttack =  (int) (Math.random()*(xp/3 + numAtkUpgrades*3 + 4) + xp/10 + numAtkUpgrades*2 + numDefUpgrades + 1);
        int criticalHitChance = (int) (Math.random() * 100);
        if (criticalHitChance < 5 + numAtkUpgrades) {
            return baseAttack * 2;
        }
        return baseAttack;
    }

    @Override
    public int defend() {
        int baseDefence =  (int) (Math.random()*(xp/4 + numDefUpgrades*3 + 3) + xp/10 + numDefUpgrades*2 + numAtkUpgrades + 1);
        int dodgeChance = (int) (Math.random() * 100);
        if (dodgeChance < 5 + numDefUpgrades) {
            return baseDefence * 2;
        }
        return baseDefence;
    }

    //let the player choose a trait of either skill path
    public void chooseTrait(Player player){
        Display.clearConsole();
        Display.printHeading("Choose a trait:");
        System.out.println("(1) " + atkUpgrades[numAtkUpgrades]);
        System.out.println("(2) " + defUpgrades[numDefUpgrades]);
        //get the players choice
        int input = Input.readInt("-> ", 2);
        Display.clearConsole();
        //deal with both cases
        if(input == 1) {
            Display.printHeading("You chose " + atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else{
            Display.printHeading("You chose " + defUpgrades[numDefUpgrades] + "!");
            numDefUpgrades++;
        }
        Input.anythingToContinue(player);
    }
}
