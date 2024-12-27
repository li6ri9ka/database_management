package GUI;

import Button_logic.ButtonNoOnTheForm;
import Button_logic.ButtonYesOnTheForm;
import Connect_DB.Connect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeletionWindow extends Application {
    private ListView<String> listView;
    private Stage primaryStage;

    public DeletionWindow(ListView<String> listView, Stage primaryStage) {
        this.listView = listView;
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane deletionWindow = new AnchorPane();
        Scene scene = new Scene(deletionWindow, 400,200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Deletion Window");

        Label textQuestion = new Label("Вы точно хотите удалить элемент?");
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        deletionWindow.setStyle("-fx-background-color: #323232");
        textQuestion.setStyle("-fx-text-fill: white;-fx-font-size: 20; -fx-font-family: 'Times New Roman'");



        yesButton.setPrefSize(120,60);
        noButton.setPrefSize(120,60);



        deletionWindow.getChildren().addAll(textQuestion, yesButton, noButton);

        AnchorPane.setTopAnchor(textQuestion, 30.0);
        AnchorPane.setLeftAnchor(textQuestion, 50.0);
        AnchorPane.setTopAnchor(yesButton, 80.0);
        AnchorPane.setLeftAnchor(yesButton, 50.0);
        AnchorPane.setTopAnchor(noButton, 80.0);
        AnchorPane.setRightAnchor(noButton, 50.0);


        primaryStage.setResizable(false);
        primaryStage.show();

        yesButton.setOnAction(new ButtonYesOnTheForm(listView, new Connect(), primaryStage));
        noButton.setOnAction(new ButtonNoOnTheForm(primaryStage));
    }

    public static void main(String[] args) {
        launch(args);

    }
}
