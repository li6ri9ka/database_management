package Button_logic;

import Connect_DB.Connect;
import GUI.UpdateWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ButtonUpdate implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    private ListView<String> listView;


    public ButtonUpdate(Stage primaryStage, ListView<String> listView) {
        this.primaryStage = primaryStage;
        this.listView = listView;
    }

    @Override
    public void handle(ActionEvent event) {
        UpdateWindow updateWindow;
        try {
            updateWindow = new UpdateWindow(listView, primaryStage,new Connect());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            updateWindow.start(primaryStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
