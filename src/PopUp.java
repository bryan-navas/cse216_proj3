import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PopUp extends VBox {
    public PopUp(String message){
        super();
        this.getChildren().add(GameStage.makeLabel(message, 20));
        Button btn = new Button("Close");
        this.getChildren().add(btn);
        this.setAlignment(Pos.CENTER);

    }

    public PopUp(Stage closeStage, GameStage gameStage){
        super();
        this.getChildren().add(GameStage.makeLabel("Do you wish to save before exiting the game?", 20));
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");
        Button cancelBtn = new Button("Cancel");
        this.getChildren().add(yesBtn);
        this.getChildren().add(noBtn);
        this.getChildren().add(cancelBtn);

        this.getChildren().get(1).setOnMouseClicked(e -> {
            File savedFile = saveFile(closeStage, "Save a file");

            if (savedFile != null) {
                String path = savedFile.getAbsolutePath();
                SavedGameInfo savedGame = new SavedGameInfo(gameStage.getWord().getWord(), gameStage.guessProgress(), gameStage.getWord().getGuessedWords(), gameStage.getHangmanPic().getPath(), gameStage.getRemainingGuesses() ,gameStage.getWord().getNumCorrectGuesses());
                savedGame.setStage(gameStage.getHangmanPic().getStage());
                try{
                    writeToFile(path, savedGame);
                    Platform.exit();
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        this.getChildren().get(2).setOnMouseClicked(e -> Platform.exit());
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }

    private File saveFile(Stage primaryStage, String dialogue) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(dialogue);
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files (*.hng)", "*.hng");
        fileChooser.getExtensionFilters().add(filter);
        return fileChooser.showSaveDialog(primaryStage);
    }

    private void writeToFile(String path, Object gameInfo) throws IOException {
        FileOutputStream fOut = new FileOutputStream(path);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);
        objOut.writeObject(gameInfo);
        objOut.close();
        fOut.close();
    }


}

