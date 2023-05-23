package com.codecool.mightytextadventure.ui;

import com.codecool.mightytextadventure.data.Player;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getStringFromUser(){
        String input = scanner.next();
        return input;
    }

    public static void anythingToContinue(Player player){
        System.out.println("\nEnter anything to continue or 'C' to display character info");
        String stringInput = getStringFromUser();
        if(stringInput.equals("C") || stringInput.equals("c")){
            Display.characterInfo(player);
        }
    }

    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                input = -1;
                System.out.println("Please enter an integer!");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

}