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

        // add code to check if user want to play
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
            System.exit(-1);
        }

        Game game = new Game();

        // TODO implement preparing the game
        System.out.println("Superb! :D Let's play!");
        game.wordToGuess();
        System.out.println(game.drawPicture());
        System.out.println("\nYour current guess: " +
                "" + game.getSecretWord());


        // TODO implement playing the game
        // ask user for a guess
        // check if user already checked that letter
            // if not check if it is in the word
                //

            // if yes ask user again about letter

        while(!game.isGameOver()){
            System.out.print("Guess a character: ");
            charToCheck = userInput.nextLine().toLowerCase().charAt(0);
            System.out.println();
            boolean tried = game.checkUserGuess(charToCheck);

            if(!tried){
                boolean foundAny = game.checkIfItIsInTheWord(charToCheck);
                if(foundAny){
                    System.out.println("Great guess! :D \n");
                    System.out.println(game.getSecretWord());
                } else {
                    System.out.println("Ups! There is no letter like this one :(\n");
                    System.out.println(game.getSecretWord());
                }
                System.out.println(game.drawPicture());
            }
        }

        // TODO add code to check if user want to play again


        userInput.close();
    }
}