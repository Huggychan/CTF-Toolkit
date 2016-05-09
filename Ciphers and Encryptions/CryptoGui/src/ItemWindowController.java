import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemWindowController implements Initializable {

	@FXML
	private HBox itemWindow;
	
	@FXML
	private TabPane tabPane;
	private SingleSelectionModel<Tab> selectionModel;
	
	private AnchorPane selectedItem;
	
	/**
	 * Initializes the sub tab system
	 * 
	 * @param fxmlFileLocation Location of FXML file representing the layout
	 * @param resources Not used
	 */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        
    	// Select first tab
    	selectionModel = tabPane.getSelectionModel();
    	selectionModel.select(tabPane.getTabs().get(0));
    	try {
			selectedItem = FXMLLoader.load(getClass().getResource("caesarCipher.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	itemWindow.getChildren().add(selectedItem);
    }
}
