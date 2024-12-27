package Button_logic;

import GUI.InsertWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ButtonInsert implements EventHandler<ActionEvent> {
    private Stage primaryStage;

    public ButtonInsert(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }




    @Override
    public void handle(ActionEvent event) {
        InsertWindow insertWindow = new InsertWindow();
        try {
            insertWindow.start(primaryStage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
