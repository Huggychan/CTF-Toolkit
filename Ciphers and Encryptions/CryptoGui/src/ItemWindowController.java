import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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

    private Node selectedItem;

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
            selectedItem = FXMLLoader.load(getClass().getResource("caesar.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        itemWindow.getChildren().add(selectedItem);

        // Add event listener for tabs
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
				if(newTab == oldTab) {
					return;
				}
				itemWindow.getChildren().remove(selectedItem);
		    	try {
					selectedItem = FXMLLoader.load(getClass().getResource(newTab.getId() + ".fxml"));
					AnchorPane.setTopAnchor(selectedItem, 0.0);
					AnchorPane.setBottomAnchor(selectedItem, 0.0);
					AnchorPane.setLeftAnchor(selectedItem, 0.0);
					AnchorPane.setRightAnchor(selectedItem, 0.0);
		    	} catch (IOException e) {
					e.printStackTrace();
				}
		    	itemWindow.getChildren().add(selectedItem);
			}
        });
    }
}
