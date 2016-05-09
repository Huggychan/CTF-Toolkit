import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label outputArea;

    /**
     * Initialize values
     * 
     * @param fxmlFileLocation File location
     * @param resources Resources
     */
    public void initialize(URL fxmlFileLocation,ResourceBundle resources) {
        doThingButton.setOnAction(event -> {
            export();
        });
    }

    private void export() {
        String input = inputArea.getText();
        if (input == "") return;
        String output = "";
        char[] iChar = input.toCharArray();
        for (int shift = 1; shift < 26; shift++) {
            for (int i = 0; i < iChar.length; i++) {
                char c = iChar[i];
                if (c <= 'z' && c >= 'a') {
                    c = (char) (c + 1);
                    if (c > 'z') c = 'a';
                    iChar[i] = c;
                }
            }
            output += "Shift by " + shift + " "+ new String(iChar) + "\n\n";
        }
        outputArea.setText(output);
    }
}
