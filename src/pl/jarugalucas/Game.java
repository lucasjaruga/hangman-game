package pl.jarugalucas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Game logic here
 */
public class Game {

    private final Integer noTries = 7;
    private ArrayList<String> dictionary;

    public Game(){
        dictionary = new ArrayList<>();
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new FileReader("E:/programming/Intellij IDEA projects/hangman-game/src/dictionary/dictionary.txt"));
            while(bufferedReader.readLine() != null){
                String word = bufferedReader.readLine();
                dictionary.add(word);
            }
        } catch (IOException e) {
            System.out.println("Couldn't find a dictionary file!");
        }


    };

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

}