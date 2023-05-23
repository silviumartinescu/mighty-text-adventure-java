package com.codecool.mightytextadventure.data;

public class Action {
    private String description;
    private Area targetArea;

    public Action(String description, Area targetArea){
        this.description = description;
        this.targetArea = targetArea;
    }

    public Action(String description) {
    }

    public String getDescription(){
        return description;
    }

    public Area getTargetArea(){
        return targetArea;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetArea(Area targetArea) {
        this.targetArea = targetArea;
    }
}
