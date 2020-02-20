import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuessWord extends FlowPane {

    private List<Button> wordButtons = new ArrayList<Button>();
    private String word;
    private List<Character> guessedWords = new ArrayList<>();
    private int numCorrectGuesses = 0;
    private boolean gameWon = false;

    public GuessWord() {
        super();
        ArrayList<String> listOfWords = new ArrayList<>();
        try  {
            Scanner s = new Scanner(new File(getClass().getResource("words.txt").toURI()));
            while (s.hasNext()) {
                listOfWords.add(s.nextLine());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
            Platform.exit();
        }
        int min = 0;
        int max = listOfWords.size()-1;
        int randomIndex = (int)(Math.random()*((max-min)+1))+min;
        this.word = (String) listOfWords.get(randomIndex);
        this.word = this.word.toUpperCase();
        makeButtons();
    }

    public GuessWord(String word){
        this.word = word;
        this.word = this.word.toUpperCase();
        makeButtons();
    }

    /*
    Makes the word into a Button[]
     */
    private void makeButtons() {
        char[] charInWord = word.toCharArray();
        Button[] blckBtns = new Button[charInWord.length];
        int i = 0;
        for (char c : charInWord) {
            wordButtons.add(new Button(Character.toString(Character.toUpperCase(c))));
            blckBtns[i] = wordButtons.get(i);
            blckBtns[i].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            blckBtns[i].setTextFill(Color.BLACK);
            i++;
        }
        this.getChildren().addAll(blckBtns);
        this.setHgap(5);

    }

    public int getIndex (char c) {
        int index = 0;
        for (Button btn : wordButtons){
           if( btn.getText().charAt(0) == c ) {
               break;
           }
           index++;
        }

        return index;
    }

    public void enableAll()
    {
        for(Button btn : wordButtons) {
            btn.setTextFill(Color.WHITE);
        }
    }

    public void fillRemaining() {
        for(Button btn : wordButtons){
            if(btn.getTextFill().equals(Color.BLACK) )
                    btn.setTextFill(Color.RED);

        }
    }

    public void updateBlackWordBox(List<Character> guessedWords){
             this.setGuessedWords(guessedWords);
             for(Character c : guessedWords){
                 makeGuess(c);
             }

    }

    public boolean makeGuess(char c){
        if (this.getWord().indexOf(c) >= 0) {

            for(Button btn : wordButtons)
            {
                if(btn.getText().charAt(0) == c) {
                    btn.setTextFill(Color.WHITE);
                    numCorrectGuesses++;
                }
            }
            if(numCorrectGuesses == wordButtons.size())
                gameWon = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getWord() {
        return word;
    }

    public int getRemainingSize() {
        return numCorrectGuesses;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Button> getWordButtons() {
        return wordButtons;
    }

    public void setWordButtons(List<Button> wordButtons) {
        this.wordButtons = wordButtons;
    }

    public void addToGuessedWords(char c){

        for (char guess : guessedWords)
        {
            if (guess == c)         //if a character is already there, dont add it
                return;
        }

        Character addChar = (Character) c;
        guessedWords.add(addChar);

    }

    public List<Character> getGuessedWords() {
        return guessedWords;
    }

    public int getNumCorrectGuesses() {
        return numCorrectGuesses;
    }

    public void setGuessedWords(List<Character> guessedWords) {
        this.guessedWords = guessedWords;
    }
}
