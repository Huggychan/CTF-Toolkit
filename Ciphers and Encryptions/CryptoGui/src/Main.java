import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            final Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            
            stage.setTitle("CTF Toolkit");
            stage.setScene(new Scene(root, 720, 540));
            stage.show();
            stage.setMinHeight(stage.getHeight());
            stage.setMinWidth(stage.getWidth());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
