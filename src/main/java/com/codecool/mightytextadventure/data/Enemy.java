package com.codecool.mightytextadventure.data;

public class Enemy extends Character {
    public static String[] enemies = {"Henchman of the Evil Emperor", "Ogre", "Evil Mercenary", "Goblin", "Stone Elemental"};

    public static String[] encounters = {"Battle", "Battle", "Battle", "Battle", "Shop"};
    int playerXp;
    public Enemy(String name, int playerXp) {
        super(name, (int) (Math.random() * playerXp + playerXp / 3 + 5), (int) (Math.random() * (playerXp / 3 + 2) + 1));
        //assigning variable
        this.playerXp = playerXp;
    }

    @Override
    public int attack() {
        int baseAttack =  (int) (Math.random()*(playerXp/2 + 2) + xp/3 + 3);
        int powerUpChance = (int) (Math.random() * 100);
        if (powerUpChance < 5) {
            return baseAttack * 2;
        }
        return baseAttack;
    }

    @Override
    public int defend() {
        int baseDefence =  (int) (Math.random()*(playerXp/3 + 1) + xp/3 + 3);
        int deflectChance = (int) (Math.random() * 100);
        if (deflectChance < 5) {
            return baseDefence * 2;
        }
        return baseDefence;
    }
}
