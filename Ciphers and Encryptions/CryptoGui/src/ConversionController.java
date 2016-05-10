import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by bird on 5/9/2016.
 */
public class ConversionController implements Initializable {
    @FXML
    private TextArea inputArea;
    @FXML
    private TextArea outputArea;
    @FXML
    private TextArea pairings;
    @FXML
    private Button convert;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        convert.setOnAction(event -> {
            convertChars();
        });
    }

    private void convertChars() { //for characters such as backslash, must input escape sequence into pairings i.e. \\
        if (inputArea.getText().equals("")) return;
        String conversions = pairings.getText();
        String input = inputArea.getText();
        int numPairings = conversions.length() - conversions.replace(";", "").length();
        if (!(conversions.charAt(conversions.length() - 1) + "").equals(";")) { //user forgot a semicolon on the last one.
            numPairings++;
            conversions += ";";
        }

        int i = 0;
        for (int count = 0; count < numPairings; count++) {
            StringBuilder prepocess = new StringBuilder();
            StringBuilder replacement = new StringBuilder();
            while (conversions.charAt(i) != ',') { //builds prepocess
                prepocess.append(conversions.charAt(i));
                i++;
            }
            i++;

            while(conversions.charAt(i) != ';') { //builds replacement
                replacement.append(conversions.charAt(i));
                i++;
            }
            i++;

            input = input.replaceAll(prepocess.toString(), replacement.toString());
        }


        outputArea.setText(input);
    }
}
