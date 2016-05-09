import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemWindowController implements Initializable {

    @FXML
    private HBox itemWindow;
    @FXML
    private BorderPane content;

    @FXML
    private TabPane tabPane;
    private SingleSelectionModel<Tab> selectionModel;

    private Node selectedItem;
    
    public static final double aspectRatio = 1.5;
    public static final int defaultWidth = 600;
    public static final int defaultHeight = (int)(defaultWidth / aspectRatio);
    public static final int margin = 40;

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
        content.setCenter(selectedItem);
        content.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox.setHgrow(content, Priority.ALWAYS);

        // Add event listener for tabs
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
			public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
				if(newTab == oldTab) {
					return;
				}
		    	try {
					selectedItem = FXMLLoader.load(getClass().getResource(newTab.getId() + ".fxml"));
					AnchorPane.setTopAnchor(selectedItem, 0.0);
					AnchorPane.setBottomAnchor(selectedItem, 0.0);
					AnchorPane.setLeftAnchor(selectedItem, 0.0);
					AnchorPane.setRightAnchor(selectedItem, 0.0);
	            	double scale = (Math.min(itemWindow.getWidth(), itemWindow.getHeight() * aspectRatio) - margin * 2) / defaultWidth;
	                selectedItem.setScaleX(scale);
	                selectedItem.setScaleY(scale);
		    	} catch (IOException e) {
					e.printStackTrace();
				}
		        content.setCenter(selectedItem);
			}
        });
        
        // Add event listeners for window resize
        itemWindow.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldNum, Number newNum) {
            	double scale = (Math.min(itemWindow.getWidth(), newNum.doubleValue() * aspectRatio) - margin * 2) / defaultWidth;
                selectedItem.setScaleX(scale);
                selectedItem.setScaleY(scale);
	        }
	    });
        itemWindow.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldNum, Number newNum) {
            	double scale = (Math.min(newNum.doubleValue(), itemWindow.getHeight() * aspectRatio) - margin * 2) / defaultWidth;
                selectedItem.setScaleX(scale);
                selectedItem.setScaleY(scale);
	        }
	    });
    }
}
