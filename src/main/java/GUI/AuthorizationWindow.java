package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuthorizationWindow extends Application {
    @Override
    public void start(Stage primaryStage){
        AnchorPane authorizationPane = new AnchorPane();
        Label passwordLabel = new Label("Пароль: ");
        PasswordField passwordField = new PasswordField();
        Button authorizationButton = new Button("Войти");
        Label passwordFailed = new Label();


        Scene scene = new Scene(authorizationPane, 400,200);

        AnchorPane.setTopAnchor(passwordLabel,10.0);
        AnchorPane.setLeftAnchor(passwordLabel,170.0);
        AnchorPane.setTopAnchor(passwordField,50.0);
        AnchorPane.setLeftAnchor(passwordField,50.0);
        AnchorPane.setTopAnchor(authorizationButton,125.0);
        AnchorPane.setLeftAnchor(authorizationButton,127.5);
        AnchorPane.setTopAnchor(passwordFailed,100.00);
        AnchorPane.setLeftAnchor(passwordFailed,50.0);

        passwordField.setPrefSize(300,50);
        authorizationButton.setPrefSize(150,50);

        authorizationPane.setStyle("-fx-background-color: #323232");
        passwordField.setStyle("-fx-background-color: #656565");
        passwordLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Times New Roman';-fx-text-fill: white");
        passwordFailed.setStyle("-fx-text-fill: red;");


        primaryStage.setScene(scene);
        authorizationPane.getChildren().addAll(passwordLabel, passwordField, authorizationButton, passwordFailed);

        primaryStage.setTitle("Authorization");
        primaryStage.setResizable(false);
        primaryStage.show();


        authorizationButton.setOnAction(event -> {
            String password = passwordField.getText();
            if (checkPassword(password)) {
                MainWindow mainWindow = new MainWindow();
                mainWindow.start(primaryStage);
            }
            else{
                passwordField.clear();
                passwordFailed.setText("Введен неверный пароль");

            }
        });
    }
    private boolean checkPassword(String password){
        return "12345".equals(password);
    }


    public static void main(String[] args) {
        launch(args);

    }
}
