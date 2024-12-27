package Button_logic;

import GUI.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ButtonNoOnTheForm implements EventHandler<ActionEvent> {
    private Stage primaryStage;

    public ButtonNoOnTheForm(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent event) {
        MainWindow mainWindow = new MainWindow();
        mainWindow.start(primaryStage);
    }
}
