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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
	
	private int counter = 1;
	
	@FXML
	private BorderPane mainWindow;
	
	@FXML
	private TabPane tabPane;
	private SingleSelectionModel<Tab> selectionModel;
	
	/**
	 * Initializes the super tab system // TODO Prevent closing last tab
	 * 
	 * @param fxmlFileLocation Location of FXML file representing the layout
	 * @param resources Not used
	 */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        selectionModel = tabPane.getSelectionModel();

        // Add first tab and new tab button
        tabPane.getTabs().add(new Tab());
        addTab();

        // Add event listener for new tab button
        selectionModel.selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldTab, Number newTab) {
                if (newTab.intValue() == tabPane.getTabs().size() - 1) {
                    addTab();
                }
            }
        });
    }
    
    public void addTab() {
    	
    	// Initialize tab
    	Tab lastTab = tabPane.getTabs().get(tabPane.getTabs().size() - 1);
    	lastTab.setText("Tab " + counter);
		lastTab.setClosable(true);
		lastTab.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                updateTabs();
            }
		});
		try {
			lastTab.setContent(FXMLLoader.load(getClass().getResource("item.fxml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
        selectionModel.select(lastTab);
        
        // Add new tab button
        lastTab = new Tab("+");
        tabPane.getTabs().add(lastTab);
        counter++;

        // Allow tabs to be closed if there is at least two
        if(this.counter == 3) {
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        }
    }
    
    public void updateTabs() {

        this.counter--;
        selectionModel.select(0);
        
        // Prevent last tab from being closed
        if(this.counter == 2) {
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        }
        
         // Reset tab text
        int counter = 1;
        for(Tab current : tabPane.getTabs()) {
            if(!current.getText().equals("+")) {
                current.setText("Tab " + counter);
                counter++;
            }
        }
    }
}