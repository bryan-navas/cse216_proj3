import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class GameStage extends VBox {
    private VBox stage;      //outermost stage that contains toolbar and figure
    private HBox toolBar;    //toolbar at the top, contains load, save, exit, start
    private HBox Figures;    //contains the hangman picture and the alphabet on screen
    public Integer remainingGuesses = 10;    //keeps track of how many guesses are remaining
    private boolean gameStarted = false;
    private boolean gameOver = false;
    private AlphabetPane alphabet;
    private GuessWord word;
    private RightStage rStage;
    private HangmanPicture hangmanPic;
    private StartGameBtn startBtn;
    private boolean firstGuessMade = false;

    public GameStage(HBox toolBar) {
        super();
        this.toolBar = toolBar;
        this.getChildren().add(toolBar);
    }

    /*
    This method will return a HBox that contains the hangman to the left and the alphabet to the right
     */
    public HBox makeFigures(AlphabetPane alphabet)
    {
        HBox figures = new HBox();
        HangmanPicture hangman = new HangmanPicture();
        this.hangmanPic = hangman;
        figures.getChildren().add(hangman);

        this.alphabet = alphabet;
        this.word = new GuessWord();

        RightStage right = new RightStage(alphabet, word);
        this.rStage = right;

        figures.getChildren().add(rStage);
        figures.setSpacing(40);

        this.Figures = figures;
        return figures;
    }

    public HBox makeFigures(AlphabetPane alphabet, String hangManPath, String newWord, List<Character> guessesMade, int stage, int guessesLeft){
        HBox figures = new HBox();
        HangmanPicture hangman = new HangmanPicture();
        hangman.setPath(hangManPath, stage);
        this.hangmanPic = hangman;
        figures.getChildren().add(hangman);

        this.alphabet = alphabet;
        this.word = new GuessWord(newWord);
        this.word.updateBlackWordBox(guessesMade);

        RightStage right = new RightStage(alphabet, word);
        right.updateGuesses(guessesLeft);
        this.rStage = right;

        figures.getChildren().add(rStage);
        figures.setSpacing(40);

        this.Figures = figures;
        return figures;


    }

    public static Label makeLabel(String title, int size ) {
        Label laberTitle = new Label(title);
        laberTitle.setFont(new Font("Times New Roman Bold", size));
        laberTitle.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(laberTitle, 0.0);
        AnchorPane.setRightAnchor(laberTitle, 0.0);
        laberTitle.setAlignment(Pos.CENTER);
        return laberTitle;
    }

    public List<Integer> guessProgress(){
        Button[] alphButtons = alphabet.getAlphabet();
        List<Integer> grayAlphIndexes = new ArrayList<>();

        int i = 0;
        for(Button btn : alphButtons)
        {
            if(btn.isDisabled())
            {
                grayAlphIndexes.add((Integer) i);
            }
            i++;
        }

        return grayAlphIndexes;
    }

    public RightStage getrStage() {
        return rStage;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public HangmanPicture getHangmanPic() {
        return hangmanPic;
    }

    public StartGameBtn getStartBtn() {
        return startBtn;
    }

    public void setStartBtn(StartGameBtn startBtn) {
        this.startBtn = startBtn;
    }

    public boolean isFirstGuessMade() {
        return firstGuessMade;
    }

    public void setFirstGuessMade(boolean firstGuessMade) {
        this.firstGuessMade = firstGuessMade;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public void setRemainingGuesses(int remainingGuesses) {
        this.remainingGuesses = remainingGuesses;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public AlphabetPane getAlphabet() {
        return alphabet;
    }

    public GuessWord getWord() {
        return word;
    }
}
