import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SavedGameInfo implements Serializable {
        private String word = "";
        private List<Integer> guessProgress = new ArrayList();   //contains the indexes which have grayed out btns
        private List<Character> guessesMade = new ArrayList();
        private String hangmanPic = "";
        private Integer remainingGuesses = 0;
        private int numCorrectGuesses = 0;
        private Integer stage = 0;

    public SavedGameInfo(String word, List<Integer> guessProgress, List<Character> guessesMade, String hangmanPic, Integer remainingGuesses, int numCorrectGuesses) {
        this.word = word;
        this.guessProgress = guessProgress;
        this.guessesMade = guessesMade;
        this.hangmanPic = hangmanPic;
        this.remainingGuesses = remainingGuesses;
        this.numCorrectGuesses = numCorrectGuesses;
        //this.stage = stage;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public void setRemainingGuesses(int remainingGuesses) {
        this.remainingGuesses = remainingGuesses;
    }

    public String getHangmanPic() {
        return hangmanPic;
    }

    public void setHangmanPic(String hangmanPic) {
        this.hangmanPic = hangmanPic;
    }

    public List getGuessProgress() {
        return guessProgress;
    }

    public void setGuessProgress(List guessProgress) {
        this.guessProgress = guessProgress;
    }

    public List getGuessesMade() {
        return guessesMade;
    }

    public void setGuessesMade(List guessesMade) {
        this.guessesMade = guessesMade;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }
}

