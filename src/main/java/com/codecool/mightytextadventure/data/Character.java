package com.codecool.mightytextadventure.data;

public abstract class Character {
    //Variables / Attributes all characters have
    public String name;
    public int maxHp, hp, xp;

    //Constructor for character
    public Character(String name, int maxHp, int xp){
        this.name = name;
        this.hp = maxHp;
        this.xp = xp;
        this.maxHp = maxHp;
    }

    //methods every character has
    public abstract int attack();
    public abstract int defend();
}

