import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;

import java.util.List;

public class AlphabetPane extends FlowPane {
    private Button[]  alphabet = new Button[26];

    public AlphabetPane() {
        super();
        char[] alphabetCharArray= "abcdefghijklmnopqrstuvwxyz".toCharArray();

        int i = 0;
        for (char c : alphabetCharArray) {
            alphabet[i] = new Button(Character.toString(Character.toUpperCase(c)));
            alphabet[i].setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            alphabet[i].setTextFill(Color.RED);
            alphabet[i].setMinSize(45,45);
            i++;
        }

        this.getChildren().addAll(alphabet);
    }

    public void updateBoard(List<Integer> guessProgress){

        for(Integer i : guessProgress){
            alphabet[i].setDisable(true);
        }

    }

    public Button[] getAlphabet() {
        return alphabet;
    }
}
