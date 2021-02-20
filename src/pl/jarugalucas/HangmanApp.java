package pl.jarugalucas;

import java.util.Scanner;

public class HangmanApp {
    public static void main(String[] args) {

        // TODO add welcome messages and to inform user what are rules of the game
        System.out.println("Welcome in the Hangman game! :)\n\n" +
                            "Rules:\n" +
                            "Main goal of the game is to guess a hidden word, character by character.\n" +
                            "You have 7 tries. When your character guess is wrong, you lose 1 try.\n" +
                            "When you lose all tries then the game is over.\n" +
                            "There also will be a picture of person which will show how many tries you still have.\n");

        // TODO add code to check if user want to play
        System.out.println("Okay, wanna play? :)" );

        boolean incorrectInput = true;
        char charToCheck;
        do {
            System.out.println("Enter 'Y' if you want to start a game or enter 'N' if not and exit from the game.");
            Scanner userInput = new Scanner(System.in);
            String userAnswer = userInput.nextLine();
            charToCheck = userAnswer.charAt(0);
            if(charToCheck == 'Y' || charToCheck == 'N')
                incorrectInput = false;

            userInput.close();
        } while(incorrectInput);

        // TODO start a game if input == 'Y' else terminate the program




        // TODO add code to check if user want to play again
    }
}