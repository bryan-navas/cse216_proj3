import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class ToolBar extends HBox {

    private Button startBtn;
    private Button loadBtn;
    private Button saveBtn;
    private Button exitBtn;

    public ToolBar () {
        super();
        startBtn = new Button ("", new ImageView(new Image("/images/New.png")));
        loadBtn  = new Button ("", new ImageView(new Image("/images/Load.png")));
        saveBtn  = new Button ("", new ImageView(new Image("/images/Save.png")));
        exitBtn  = new Button ("", new ImageView(new Image("/images/Exit.png")));
        saveBtn.setDisable(true);   //before starting a game the save button will always be disabled by default
        this.getChildren().add(startBtn);
        this.getChildren().add(loadBtn);
        this.getChildren().add(saveBtn);
        this.getChildren().add(exitBtn);
        this.setBackground( new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Button getStartBtn() {
        return startBtn;
    }

    public void setStartBtn(Button startBtn) {
        this.startBtn = startBtn;
    }

    public Button getExitBtn() {
        return exitBtn;
    }

    public void setExitBtn(Button exitBtn) {
        this.exitBtn = exitBtn;
    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    public Button getLoadBtn() {
        return loadBtn;
    }

    public void setLoadBtn(Button loadBtn) {
        this.loadBtn = loadBtn;
    }
}
