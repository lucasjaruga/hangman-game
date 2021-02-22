package pl.jarugalucas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Game engine class - responsible for a game logic
 */
public class Game {

    private int userTries;
    private final ArrayList<String> dictionary;
    public String wordToGuess;
    private StringBuilder secretWord;
    public ArrayList<Character> userGuesses;

    public Game(){
        userGuesses = new ArrayList<>();
        dictionary = new ArrayList<>();
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("E:/programming/Intellij IDEA projects/hangman-game/src/dictionary/dictionary.txt"));
            while(bufferedReader.readLine() != null){
                String word = bufferedReader.readLine();
                dictionary.add(word);
            }
        } catch (IOException e) {
            System.out.println("Couldn't find a dictionary file! Sorry, we won't play :(");
        }
    }

    // choose word to guess and prepare secretWord
    public void wordToGuess(){
        Random random = new Random();
        int wordIndex = random.nextInt(dictionary.size());
        wordToGuess = dictionary.get(wordIndex);

        int wordLength = wordToGuess.length();
        secretWord = new StringBuilder();
        for(int i = 0; i < wordLength * 2; i++){
            if(i%2 == 0){
                secretWord.append("_");
            } else {
                secretWord.append(" ");
            }
        }
    }

    public String getSecretWord(){
        return secretWord.toString();
    }

    public void setUserTries(Integer userTries) {
        this.userTries = userTries;
    }

    public void prepareGame(){
        System.out.println("Superb! :D Let's play!");
        wordToGuess();
        setUserTries(0);
        userGuesses.clear();
        System.out.println(drawPicture());
        System.out.println("\nWord to guess: " +
                "" + getSecretWord());
    }

    public boolean isFinished(){

        boolean result;
        if(userUsedAllTries()) {
            System.out.println("\nGAME OVER! You used all your tries :(");
            System.out.println("The word was: " + wordToGuess);
            if(wantToPlayAgain()){
                prepareGame();
                result = false;
            } else {
                System.out.println("Exiting the game... bye bye!");
                result = true;
            }
        } else if (userGuessedTheWord()) {
            System.out.println("\nGratz! You did it! This is a correct word! :)");
            if (wantToPlayAgain()) {
                prepareGame();
                result = false;
            } else {
                System.out.println("Exiting the game... bye bye!");
                result = true;
            }
        } else {
            result = false;
        }

        return result;
    }

    public boolean userUsedAllTries(){
        final int maxTries = 7;
        return userTries == maxTries;
    }

    public boolean userGuessedTheWord(){

        StringBuilder userGuess = new StringBuilder();
        for(int i = 0; i < secretWord.length(); i++){
            if(i%2 == 0){
                userGuess.append(secretWord.charAt(i));
            }
        }
        return userGuess.toString().equals(wordToGuess);
    }

    public boolean wantToPlayAgain(){
        boolean incorrectInput = true;
        char charToCheck;
        do {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Wanna play again? Enter 'Y' or 'N'");
            String userAnswer = userInput.nextLine();
            charToCheck = userAnswer.toLowerCase().charAt(0);
            if(charToCheck == 'y' || charToCheck == 'n')
                incorrectInput = false;

        } while(incorrectInput);

        return charToCheck == 'y';
    }

    public boolean checkUserGuess(char userCharGuess){
        // check if user already used that character, if not add to list
        boolean alreadyCheck = false;
        Iterator iterator = userGuesses.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().equals(userCharGuess)) {
                System.out.println("You've already tried this one. Try another one :)");
                alreadyCheck = true;
                break;
            }
        }

        //if not already check then add to a list
        if(!alreadyCheck){
            userGuesses.add(userCharGuess);
        }

        return alreadyCheck;
    }

    public boolean checkIfItIsInTheWord(char userCharGuess){
        boolean foundAny = false;
        for(int i = 0; i < wordToGuess.length(); i++){
            if(wordToGuess.charAt(i) == userCharGuess){
                secretWord.setCharAt(i * 2, userCharGuess);
                foundAny = true;
            }
        }

        if(foundAny){
            return true;
        } else {
            userTries++;
            return false;
        }
    }

    /**
     * method responsible for drawing picture
     *
     * @return an appropriate picture of hangman based on used tries by player
     */
    public String drawPicture(){
        /*
            Picture to use

            "  - - - - -\n"+
            " |        |\n"+
            " |        O\n" +
            " |      / | \\ \n"+
            " |        |\n" +
            " |       / \\ \n" +
            " |\n" +
            "/ \\n";
         */

        switch (userTries){
            case 1: return  "  - - - - -\n"+
                            " |        |\n"+
                            " |        O\n" +
                            " |       \n"+
                            " |        \n" +
                            " |       \n" +
                            " |\n" +
                            "/ \\";

            case 2: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |        O\n" +
                    " |        | \n"+
                    " |        \n" +
                    " |        \n" +
                    " |\n" +
                    "/ \\";
            case 3: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |        O\n" +
                    " |        |\\  \n"+
                    " |        \n" +
                    " |        \n" +
                    " |\n" +
                    "/ \\";
            case 4: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |        O\n" +
                    " |      / | \\ \n"+
                    " |        \n" +
                    " |         \n" +
                    " |\n" +
                    "/ \\";
            case 5: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |        O\n" +
                    " |      / | \\ \n"+
                    " |        |\n" +
                    " |        \n" +
                    " |\n" +
                    "/ \\";
            case 6: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |        O\n" +
                    " |      / | \\ \n"+
                    " |        |\n" +
                    " |       / \n" +
                    " |\n" +
                    "/ \\";
            case 7: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |        O\n" +
                    " |      / | \\ \n"+
                    " |        |\n" +
                    " |       / \\ \n" +
                    " |\n" +
                    "/ \\";
            default: return  "  - - - - -\n"+
                    " |        |\n"+
                    " |         \n" +
                    " |         \n"+
                    " |         \n" +
                    " |         \n" +
                    " |         \n" +
                    "/ \\ ";
        }
    }
}