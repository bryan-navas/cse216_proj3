import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class StartGameBtn extends HBox {
    private Button startBtn;

    public StartGameBtn(GameStage stage){
        super();
        this.startBtn = new Button("Start Game");
        this.getChildren().add(startBtn);
        this.setBackground( new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);

        startBtn.setOnMouseClicked(e -> {
            startBtn.setDisable(true);
            stage.setGameStarted(true);
        });

    }


}
