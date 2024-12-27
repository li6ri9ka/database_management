package Button_logic;

import GUI.DeletionWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ButtonDeletion implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private ListView<String> listView;

    public ButtonDeletion(Stage primaryStage, ListView<String> listView) {
        this.primaryStage = primaryStage;
        this.listView = listView;
    }



    @Override
    public void handle(ActionEvent event) {
        DeletionWindow deletionWindow = new DeletionWindow(listView, primaryStage);
        try {
            deletionWindow.start(primaryStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
