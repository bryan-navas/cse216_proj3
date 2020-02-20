import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HangmanPicture extends ImageView {
    private int stage = 0;
    private String path = "/images/stage" + stage + ".jpg";
    public HangmanPicture(){
        super(new Image("/images/stage0.jpg"));
    }

    public void updatePic(){
        stage++;
        path = "/images/stage" + stage + ".jpg";
        this.setImage(new Image(path));
    }

    public void setPic(int stage) {
        this.stage = stage;
        this.path = "/images/stage" + stage + ".jpg";
        this.setImage(new Image(path));

    }

    public String getPath() {
        return path;
    }

    public int getStage(){
        return stage;
    }

    public void setPath(String path, int stage) {
        this.path = path;
        this.stage = stage;
        this.setImage(new Image(path));
    }
}
