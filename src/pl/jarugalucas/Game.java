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
    private String wordToGuess;
    private StringBuilder currentUserGuessedWord;
    public ArrayList<Character> userGuessedCharacters;

    public Game(){
        userGuessedCharacters = new ArrayList<>();
        dictionary = new ArrayList<>();

        // loading a dictionary from txt file
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

    /**
     *  method used to choose a word to guessing. It's randomly picked from dictionary
     */
    public void wordToGuess(){
        Random random = new Random();
        int wordIndex = random.nextInt(dictionary.size());
        wordToGuess = dictionary.get(wordIndex);

        int wordLength = wordToGuess.length();
        currentUserGuessedWord = new StringBuilder();
        for(int i = 0; i < wordLength * 2; i++){
            if(i%2 == 0){
                currentUserGuessedWord.append("_");
            } else {
                currentUserGuessedWord.append(" ");
            }
        }
    }

    /**
     * method used to return current state of guessed word by player
     *
     * @return a String which represents current state of guessed word by player
     */
    public String getCurrentUserGuessedWord(){
        return currentUserGuessedWord.toString();
    }

    /**
     * method used to set current number of user attempts
     *
     * @param userTries - an Integer which represents how many attempts user already took to guess a character in word
     */
    public void setUserTries(Integer userTries) {
        this.userTries = userTries;
    }

    /**
     *  method used to prepare application before user will provide any input
     */
    public void prepareGame(){
        System.out.println("Superb! :D Let's play!");
        wordToGuess();
        setUserTries(0);
        userGuessedCharacters.clear();
        System.out.println(drawPicture());
        System.out.println("\nWord to guess: " +
                "" + getCurrentUserGuessedWord());
    }

    /**
     *  Method used to verify that game should still continue or not.
     *  Method checks if player already guessed a word OR if player already used all of his possible attempts.
     *  If player won or lost, game will ask player if player wants to play again or exit a game.
     *
     * @return a boolean - which tells if game is over or not
     */
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

    /**
     *  Method used to verify if user used all possible attempts
     *
     * @return boolean - which indicates if user used all possible attempts
     */
    public boolean userUsedAllTries(){
        final int maxTries = 7;
        return userTries == maxTries;
    }

    /**
     *  Method used to verify if user guessed a word.
     *
     * @return boolean - which indicates if user guessed a word
     */
    public boolean userGuessedTheWord(){

        StringBuilder userGuess = new StringBuilder();
        for(int i = 0; i < currentUserGuessedWord.length(); i++){
            if(i%2 == 0){
                userGuess.append(currentUserGuessedWord.charAt(i));
            }
        }
        return userGuess.toString().equals(wordToGuess);
    }

    /**
     *  Method used to ask player if he/she wants to play again or not
     *
     * @return boolean - which represents player decision about continuing a game
     */
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

    /**
     *  Method used to check if player already checked that character
     *
     * @param userCharGuess - a char which represents current player guess about character in a word to guess
     * @return boolean - which indicates if user already checked that character
     */
    public boolean checkUserGuess(char userCharGuess){
        // check if user already used that character, if not add to list
        boolean alreadyCheck = false;
        Iterator<Character> iterator = userGuessedCharacters.iterator();
        while(iterator.hasNext()) {
            if (iterator.next().equals(userCharGuess)) {
                System.out.println("You've already tried this one. Try another one :)");
                alreadyCheck = true;
                break;
            }
        }

        //if not already check then add to a list
        if(!alreadyCheck){
            userGuessedCharacters.add(userCharGuess);
        }

        return alreadyCheck;
    }

    /**
     *  Method used to check if player guessed character is in a word
     *
     * @param userCharGuess - a char which represents current player guess about character in a word to guess
     * @return boolean - which indicates if player found a character
     */
    public boolean checkIfItIsInTheWord(char userCharGuess){
        boolean foundAny = false;
        for(int i = 0; i < wordToGuess.length(); i++){
            if(wordToGuess.charAt(i) == userCharGuess){
                currentUserGuessedWord.setCharAt(i * 2, userCharGuess);
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