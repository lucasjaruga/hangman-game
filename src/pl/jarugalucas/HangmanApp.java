package pl.jarugalucas;

import java.util.Scanner;

public class HangmanApp {
    public static void main(String[] args) {

        // add welcome messages and to inform user what are rules of the game
        System.out.println("Welcome in the Hangman game! :)\n\n" +
                            "Rules:\n" +
                            "Main goal of the game is to guess a hidden word, character by character.\n" +
                            "You have 7 tries. When your character guess is wrong, you lose 1 try.\n" +
                            "When you lose all tries then the game is over.\n" +
                            "There also will be a picture of person which will show how many tries you still have.\n");

        // check if user want to play
        System.out.println("Okay, wanna play? :)" );
        Scanner userInput = new Scanner(System.in);
        boolean incorrectInput = true;
        char charToCheck;
        do {
            System.out.println("Enter 'Y' if you want to start a game or enter 'N' if not and exit from the game.");
            String userAnswer = userInput.nextLine();
            charToCheck = userAnswer.toLowerCase().charAt(0);
            if(charToCheck == 'y' || charToCheck == 'n')
                incorrectInput = false;

        } while(incorrectInput);

        // start a game if input == 'Y' ELSE terminate the program
        if(charToCheck == 'n'){
            userInput.close();
            System.out.println("Exiting the game... bye bye!");
            System.exit(-1);
        }

        // preparing the game
        Game game = new Game();
        game.prepareGame();

        // playing the game
        while(!game.isFinished()){
            System.out.print("Guess a character: ");
            charToCheck = userInput.nextLine().toLowerCase().charAt(0);
            System.out.println();
            boolean tried = game.checkUserGuess(charToCheck);

            if(!tried){
                boolean foundAny = game.checkIfItIsInTheWord(charToCheck);
                if(foundAny){
                    System.out.println("Great guess! :D \n");
                } else {
                    System.out.println("Ups! There is no letter like this one :(\n");
                }
                System.out.println(game.drawPicture() + "\n");
                System.out.println("Word to guess: " + game.getCurrentUserGuessedWord());
            }
        }

        userInput.close();
    }
}