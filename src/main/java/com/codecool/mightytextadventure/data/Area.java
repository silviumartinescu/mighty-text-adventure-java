package com.codecool.mightytextadventure.data;

public class Area {
    private final String name;
    private String description;
    private Action[] actions;

    public String getName() {
        return name;
    }

    public Area(String name, String description, Action[] actions) {
        this.name = name;
        this.description = description;
        this.actions = actions;
    }

    public String getDescription() {
        return description;
    }

    public void setActions(Action[] actions) {
        this.actions = actions;
    }

    public Action[] getActions(){
        return actions;
    }

    public static Area[] loadAreas(){
        Area[] areas = new Area[11];
        areas[0] = new Area("The Adventurer's Guild","This bustling hub of activity is where your journey begins." +
                "\nGather your courage and step forth into the unknown, for the world of adventure awaits!", new Action[]{});
        areas[1] = new Area("The Dark Forest","A dense forest filled with twisted trees, dangerous creatures, and a sense of foreboding.", new Action[]{});
        areas[2] = new Area("The Crystal Cavern","A sparkling cavern filled with glittering gems and crystals, but watch out for the fierce beasts that guard them.", new Action[]{});
        areas[3] = new Area("The Flames of Fury"," A fiery room where lava flows freely and the heat is intense. Be careful, one misstep and you'll be burned to a crisp.", new Action[]{});
        areas[4] = new Area("The Arctic Tundra", "A frozen wasteland where the wind howls and the snowdrifts are deep. Stay warm and watch out for the deadly blizzards.", new Action[]{});
        areas[5] = new Area("The Healing Haven","A tranquil shop that specializes in health potions. Rejuvenate yourself and gain the strength to overcome any obstacle.", new Action[]{});
        areas[6] = new Area("The Winding Path","A twisting, turning path that leads deep into the heart of the dungeon. Be prepared for anything, as you never know what lies around the next bend.", new Action[]{});
        areas[7] = new Area("The Poisonous Swamp", "A fetid swamp filled with toxic plants and creatures. Move carefully, as one misstep could spell your doom.", new Action[]{});
        areas[8] = new Area("The Thundering Mountain","A room filled with echoes of thunder and lightning. Keep your wits about you, as the earthquakes are unpredictable and dangerous.", new Action[]{});
        areas[9] = new Area("The Sorcerer's Citadel", "This towering fortress is the seat of power for the evil sorcerer.\nIt is here that you will confront the source of evil that threatens the land and put an end to the darkness once and for all.\nMay the strength of your sword and the cunning of your mind see you through to victory.", new Action[]{});
        areas[10] = new Area("END", "THIS IS THE END", new Action[]{});

        areas[0].setActions(new Action[]{
                new Action("Go to The Dark Forest", areas[1]),
        });
        areas[1].setActions(new Action[]{
                new Action("Go to The Crystal Cavern", areas[2]),
                new Action("Go to The Arctic Tundra", areas[4]),
        });
        areas[2].setActions(new Action[]{
                new Action("Go to The Dark Forest", areas[1]),
                new Action("Go to The Flames of Fury", areas[3]),
        });
        areas[3].setActions(new Action[]{
                new Action("Go to The Crystal Cavern", areas[2]),
                new Action("Go to The Arctic Tundra", areas[4]),
                new Action("Go to The Winding Path", areas[6])
        });
        areas[4].setActions(new Action[]{
                new Action("Go to The Dark Forest", areas[1]),
                new Action("Go to The Flames of Fury", areas[3]),
                new Action("Go to The Poisonous Swamp", areas[7]),
        });
        areas[5].setActions(new Action[]{
                new Action("Go to The Winding Path", areas[6]),
                new Action("Go to The Thundering Mountain", areas[8]),
        });
        areas[6].setActions(new Action[]{
                new Action("Go to The Flames of Fury", areas[3]),
                new Action("Go to The Healing Haven", areas[5]),
                new Action("Go to The Poisonous Swamp", areas[7])
        });
        areas[7].setActions(new Action[]{
                new Action("Go to The Arctic Tundra", areas[4]),
                new Action("Go to The Winding Path", areas[6]),
                new Action("Go to The Thundering Mountain", areas[8])
        });
        areas[8].setActions(new Action[]{
                new Action("Go to The The Healing Haven", areas[5]),
                new Action("Go to The Poisonous Swamp", areas[7]),
                new Action("Go to The Sorcerer's Citadel", areas[9])
        });
        areas[9].setActions(new Action[]{
                new Action("Exit", areas[10]),
        });
        return areas;
    }
}