import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.*;


public class Hangman extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hangman");
        primaryStage.getIcons().add(new Image("/images/Hangman.png"));
        ToolBar gameToolBar = new ToolBar();   //this object holds start, load, save and exit buttons
        GameStage gameStage = new GameStage(gameToolBar);   //this vBox is basically our main stage
        primaryStage.setScene(new Scene(gameStage, 1000,572)); //primaryStage.getScene().setRoot()
        primaryStage.show();

        gameToolBar.getChildren().get(0).setOnMouseClicked(e -> {     //starts a new game
            if(!gameStage.isGameStarted()) {
                startGame(primaryStage, gameStage);
            }
            else{
                gameStage.getChildren().clear();
                gameStage.setRemainingGuesses(10);
                gameStage.setGameOver(false);
                gameStage.setFirstGuessMade(false);
                gameStage.getChildren().add(gameToolBar);
                primaryStage.getScene().setRoot(gameStage);
                startGame(primaryStage, gameStage);
            }
        });

        gameToolBar.getChildren().get(1).setOnMouseClicked(e -> {     //loading a file
            if(!gameStage.isFirstGuessMade()) {
                File loadedFile = loadFile(primaryStage, "Load a file");
                if (loadedFile != null) {
                    String loadPath = loadedFile.getAbsolutePath();
                    try {
                        SavedGameInfo gameInfo = (SavedGameInfo) readFromFile(loadPath);
                        Label hangmanTitle = GameStage.makeLabel("Hangman", 38);
                        gameStage.getChildren().add(hangmanTitle);
                        AlphabetPane alphabet = new AlphabetPane();     //the keyboard
                        alphabet.updateBoard(gameInfo.getGuessProgress());
                        HBox hngManAndAlph = gameStage.makeFigures(alphabet, gameInfo.getHangmanPic(), gameInfo.getWord(), gameInfo.getGuessesMade(), gameInfo.getStage(), gameInfo.getRemainingGuesses());
                        gameStage.getChildren().add(2, hngManAndAlph);
                        gameStage.setRemainingGuesses(gameInfo.getRemainingGuesses());
                        gameStage.setGameStarted(true);
                        gameStage.setGameOver(false);
                        StartGameBtn startBtn = new StartGameBtn(gameStage);
                        gameStage.getChildren().add(startBtn);
                        gameStage.setStartBtn(startBtn);

                    } catch (Exception fileE) {
                        fileE.printStackTrace();
                    }
                }
                else{   //if in the middle of a game
                        //do nothing based on hw doc

                }
            }
        });


        gameToolBar.getChildren().get(2).setOnMouseClicked(e -> {       //deal with saving
            File savedFile = saveFile(primaryStage, "Save a file");

            if (savedFile != null) {
                String path = savedFile.getAbsolutePath();
                SavedGameInfo savedGame = new SavedGameInfo(gameStage.getWord().getWord(), gameStage.guessProgress(), gameStage.getWord().getGuessedWords(), gameStage.getHangmanPic().getPath(), gameStage.getRemainingGuesses() ,gameStage.getWord().getNumCorrectGuesses());
                savedGame.setStage(gameStage.getHangmanPic().getStage());
                try{
                    writeToFile(path, savedGame);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });

        gameToolBar.getChildren().get(3).setOnMouseClicked(e -> {
            if (gameStage.isFirstGuessMade() && !gameStage.isGameOver()){
                    askClosePopUp(primaryStage, gameStage);
            }
            else
                Platform.exit();
        });  //sets functionality to exit button

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            if(gameStage.isGameStarted() && !gameStage.isGameOver() ){                      //if game has started do this
                gameToolBar.getChildren().get(2).setDisable(false);   //enables the save btn once a new game starts
                gameStage.setFirstGuessMade(true);
                gameStage.getStartBtn().setDisable(true);
                if (event.getCode().isLetterKey()) {
                    char charPressed = event.getCode().getName().charAt(0);
                    int ascii = (int)charPressed - 65;
                    gameStage.getAlphabet().getChildren().get(ascii).setDisable(true);   //disables it in the keyboard on screen

                    //if the char was in the word to be guessed
                    if( !gameStage.getWord().getGuessedWords().contains((Character)charPressed) && gameStage.getWord().makeGuess(charPressed)){     //if the word contains the char
                        gameStage.getWord().getGuessedWords().add((Character) charPressed);
                        if(gameStage.getWord().isGameWon())  //game is won
                        {
                            makePopUp(gameStage, "Game Over", "You won! The word was: ");
                            gameStage.getWord().enableAll();
                            gameStage.setGameOver(true);
                            gameToolBar.getChildren().get(2).setDisable(true);
                        }
                    }
                    else if(!(gameStage.getWord().getGuessedWords().contains((Character)charPressed))) {   //the guess was wrong
                            gameStage.setRemainingGuesses(gameStage.getRemainingGuesses()-1);
                            //update the hangman pic
                            gameStage.getHangmanPic().updatePic();
                            if(gameStage.getRemainingGuesses() == 0)
                            {
                                gameStage.setGameOver(true);
                                gameStage.getrStage().updateGuesses(gameStage.getRemainingGuesses());
                                makePopUp(gameStage, "Game Over", "You lost, the word was: ");
                                gameStage.getWord().fillRemaining();
                                gameToolBar.getChildren().get(2).setDisable(true);
                            }
                            if (!gameStage.isGameOver()) {
                                gameStage.getWord().addToGuessedWords(charPressed);
                                gameStage.getrStage().updateGuesses(gameStage.getRemainingGuesses());
                            }
                    }
                }
            }
            else{
                //if game has not started, do nothing
            }
            event.consume();
        });
    }

    private void startGame(Stage primaryStage, GameStage gameStage) {
        Label hangmanTitle = GameStage.makeLabel("Hangman", 38);
        AlphabetPane alphabet = new AlphabetPane();                              //returns an object with 26 buttons labeled A-Z
        gameStage.getChildren().add(hangmanTitle);                               //sets title
        HBox hangmanPicAndAlphabet = gameStage.makeFigures(alphabet);     //makes an HBox with hangman pic and alphabet, hangman pic can be updated by obj.getChildren().set(0).newImage()
        gameStage.getChildren().add(hangmanPicAndAlphabet);             //adds the VBox containing the hangman picture and the alphabet
        gameStage.setGameStarted(true);   //indicates game has started
        StartGameBtn startBtn = new StartGameBtn(gameStage);
        gameStage.getChildren().add(startBtn);
        gameStage.setStartBtn(startBtn);

        primaryStage.getScene().setRoot(gameStage);
        gameStage.setGameStarted(true);
    }

    private File saveFile(Stage primaryStage, String dialogue) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(dialogue);
        ExtensionFilter filter = new ExtensionFilter("Text Files (*.hng)", "*.hng");
        fileChooser.getExtensionFilters().add(filter);
        return fileChooser.showSaveDialog(primaryStage);
    }

    private File loadFile(Stage primaryStage, String dialogue) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(dialogue);
        ExtensionFilter filter = new ExtensionFilter("Text Files (*.hng)", "*.hng");
        fileChooser.getExtensionFilters().add(filter);
        return fileChooser.showOpenDialog(primaryStage);
    }

    private void writeToFile(String path, Object gameInfo) throws IOException {
        FileOutputStream fOut = new FileOutputStream(path);
        ObjectOutputStream objOut = new ObjectOutputStream(fOut);
        objOut.writeObject(gameInfo);
        objOut.close();
        fOut.close();
    }

    private Object readFromFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fIn = new FileInputStream(path);
        ObjectInputStream objIn = new ObjectInputStream(fIn);
        return objIn.readObject();

    }

    private void makePopUp(GameStage gameStage, String title, String message) {
        Stage popUpStage = new Stage();
        popUpStage.setTitle(title);
        popUpStage.getIcons().add(new Image("/images/Hangman.png"));
        PopUp popWindow = new PopUp(message + gameStage.getWord().getWord());
        popUpStage.setScene(new Scene(popWindow, 500, 100));
        popUpStage.show();
        popWindow.getChildren().get(1).setOnMouseClicked(e -> {
            popUpStage.close();
        });
    }

    private void askClosePopUp(Stage primaryStage, GameStage gameStage){
        Stage closeStage = new Stage();
        closeStage.setTitle("Ending Game");
        closeStage.getIcons().add(new Image("/images/Hangman.png"));
        PopUp closingPopUp = new PopUp(primaryStage, gameStage);
        closeStage.setScene(new Scene(closingPopUp, 500, 200));
        closeStage.show();
        closingPopUp.getChildren().get(3).setOnMouseClicked(e -> closeStage.close());

    }


}
