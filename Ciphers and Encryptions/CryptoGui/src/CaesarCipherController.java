import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by bird on 5/8/2016.
 */
public class CaesarCipherController implements Initializable {
    @FXML
    private TextArea inputArea;
    @FXML
    private Button doThingButton;
    @FXML
    private TextArea outputArea;

    public void initialize(URL fxmlFileLocation,ResourceBundle resources) {
        doThingButton.setOnAction(event -> {
            System.out.println("command");
        });
    }
}
