import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class RightStage extends VBox {
    private int remainingGuesses = 10;

    public RightStage(AlphabetPane alphabet, GuessWord word) {
        super(15);
        this.getChildren().add(this.makeLabel("Remaining Guesses: " + remainingGuesses, 20));
        this.getChildren().add(word);
        this.getChildren().add(alphabet);

    }

    public Label makeLabel(String title, int size ) {
        Label hangmanTitle = new Label(title);
        hangmanTitle.setFont(new Font("Times New Roman Bold", size));
        hangmanTitle.setMaxWidth(Double.MAX_VALUE);
        AnchorPane.setLeftAnchor(hangmanTitle, 0.0);
        AnchorPane.setRightAnchor(hangmanTitle, 0.0);
        hangmanTitle.setAlignment(Pos.CENTER);
        return hangmanTitle;
    }

    public void updateGuesses(int guessesLeft)
    {
        this.getChildren().set(0, this.makeLabel("Remaining Guesses: " + guessesLeft, 20));
    }
}
