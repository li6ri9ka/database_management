package Button_logic;
import GUI.AuthorizationWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Exit implements EventHandler<ActionEvent> {
    private Stage primaryStage;

    public Exit(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent event) {
        AuthorizationWindow authorizationWindow = new AuthorizationWindow();
        authorizationWindow.start(primaryStage);
    }
}
