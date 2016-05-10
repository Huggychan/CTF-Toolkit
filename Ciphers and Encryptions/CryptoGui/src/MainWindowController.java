import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
	
	@FXML
	private BorderPane mainWindow;
	
	@FXML
	private TabPane tabPane;
	private SingleSelectionModel<Tab> selectionModel;
	
	private boolean busy = false; // TODO Maybe improve
	
	/**
	 * Initializes the super tab system
	 * 
	 * @param fxmlFileLocation Location of FXML file representing the layout
	 * @param resources Not used
	 */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        selectionModel = tabPane.getSelectionModel();

        // Add new tab button and first tab
        tabPane.getTabs().add(new Tab("+"));
        addTab();

        // Add event listener for new tab button
        selectionModel.selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldTab, Number newTab) {
                if(!busy && newTab.intValue() == 0) {
                    addTab();
                }
            }
        });
    }
    
    /**
     * Creates a new tab.
     */
    public void addTab() {

    	// Initialize tab
    	Tab newTab = new Tab("  Caesar " + tabPane.getTabs().size() + "  ");
		newTab.setClosable(true);
		newTab.setOnCloseRequest(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                busy = true;
            }
        });
		newTab.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                updateTabs();
                busy = false;
            }
		});
		try {
			newTab.setContent(FXMLLoader.load(getClass().getResource("item.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
        tabPane.getTabs().add(newTab);
        selectionModel.selectLast();

        // Allow tabs to be closed if there are at least two tabs
        if(tabPane.getTabs().size() == 3) {
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        }
    }
    
    /**
     * Does cleanup work when a tab is closed.
     */
    public void updateTabs() {

        // Prevent last tab from being closed
        if(tabPane.getTabs().size() == 2) {
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        }
        
        // Reset tab text
        int counter = 1;
        for(Tab current : tabPane.getTabs()) {
            if(!current.getText().equals("+")) {
                TabPane pane = (TabPane) ((HBox)current.getContent()).getChildren().get(0);
                current.setText("  " + pane.getSelectionModel().getSelectedItem().getText() + " " + counter + "  ");
                counter++;
            }
        }
        
        if(selectionModel.getSelectedIndex() == 0) {
            selectionModel.select(1);
        }
    }
}