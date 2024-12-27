package GUI;

import Button_logic.ButtonInsertOnTheForm;
import Connect_DB.Connect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InsertWindow extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane insertWindow = new AnchorPane();
        Scene scene = new Scene(insertWindow, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Insert Window");

        insertWindow.setStyle("-fx-background-color: #323232");

        Label insertNameLabel = new Label("Введите имя: ");
        Label insertFirstNameLabel = new Label("Введите фамилию: ");
        Label insertMiddleNameLabel = new Label("Введите отчество: ");
        Label insertDisciplineLabel = new Label("Введите дисциплину: ");
        Label insertWeightCategoryLabel = new Label("Введите весовую категорию:");
        Label insertCountryLabel = new Label("Введите страну: ");

        TextField insertNameTextField = new TextField();
        TextField insertFirstNameTextField = new TextField();
        TextField insertMiddleNameTextField = new TextField();
        TextField insertDisciplineTextField = new TextField();
        TextField insertWeightCategoryTextField = new TextField();
        TextField insertCountryTextField = new TextField();

        Button insertButtonOnTheForm = new Button("Добавить");

        insertNameLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        insertFirstNameLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        insertMiddleNameLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        insertDisciplineLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        insertWeightCategoryLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        insertCountryLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");


        insertNameTextField.setStyle("-fx-font-size: 20");
        insertFirstNameTextField.setStyle("-fx-font-size: 20");
        insertMiddleNameTextField.setStyle("-fx-font-size: 20");
        insertDisciplineTextField.setStyle("-fx-font-size: 20");
        insertWeightCategoryTextField.setStyle("-fx-font-size: 20");
        insertCountryTextField.setStyle("-fx-font-size: 20");

        insertFirstNameTextField.setPrefSize(300,40);
        insertNameTextField.setPrefSize(300,40);
        insertMiddleNameTextField.setPrefSize(300,40);
        insertDisciplineTextField.setPrefSize(300,40);
        insertWeightCategoryTextField.setPrefSize(300,40);
        insertCountryTextField.setPrefSize(300,40);

        insertButtonOnTheForm.setPrefSize(250,80);


        AnchorPane.setTopAnchor(insertFirstNameLabel, 50.0);
        AnchorPane.setLeftAnchor(insertFirstNameLabel, 50.0);

        AnchorPane.setTopAnchor(insertNameLabel, 120.0);
        AnchorPane.setLeftAnchor(insertNameLabel, 50.0);

        AnchorPane.setTopAnchor(insertMiddleNameLabel, 190.0);
        AnchorPane.setLeftAnchor(insertMiddleNameLabel, 50.0);

        AnchorPane.setTopAnchor(insertDisciplineLabel, 260.0);
        AnchorPane.setLeftAnchor(insertDisciplineLabel, 50.0);

        AnchorPane.setTopAnchor(insertWeightCategoryLabel, 330.0);
        AnchorPane.setLeftAnchor(insertWeightCategoryLabel, 50.0);

        AnchorPane.setTopAnchor(insertCountryLabel, 400.0);
        AnchorPane.setLeftAnchor(insertCountryLabel, 50.0);



        AnchorPane.setTopAnchor(insertFirstNameTextField, 50.0);
        AnchorPane.setLeftAnchor(insertFirstNameTextField, 320.0);

        AnchorPane.setTopAnchor(insertNameTextField, 120.0);
        AnchorPane.setLeftAnchor(insertNameTextField, 250.0);

        AnchorPane.setTopAnchor(insertMiddleNameTextField, 190.0);
        AnchorPane.setLeftAnchor(insertMiddleNameTextField, 315.0);

        AnchorPane.setTopAnchor(insertDisciplineTextField, 260.0);
        AnchorPane.setLeftAnchor(insertDisciplineTextField, 350.0);

        AnchorPane.setTopAnchor(insertWeightCategoryTextField, 330.0);
        AnchorPane.setLeftAnchor(insertWeightCategoryTextField, 440.0);

        AnchorPane.setTopAnchor(insertCountryTextField, 400.0);
        AnchorPane.setLeftAnchor(insertCountryTextField, 280.0);

        AnchorPane.setTopAnchor(insertButtonOnTheForm, 480.0);
        AnchorPane.setLeftAnchor(insertButtonOnTheForm, 260.0);




        insertWindow.getChildren().addAll(insertNameLabel,insertFirstNameLabel,insertMiddleNameLabel,insertDisciplineLabel,insertWeightCategoryLabel,insertCountryLabel,
                insertFirstNameTextField,insertNameTextField,insertMiddleNameTextField,insertDisciplineTextField,insertWeightCategoryTextField,insertCountryTextField, insertButtonOnTheForm);

        insertButtonOnTheForm.setOnAction(new ButtonInsertOnTheForm(new MainWindow(),primaryStage,insertFirstNameTextField, insertNameTextField, insertMiddleNameTextField,insertDisciplineTextField,insertWeightCategoryTextField,insertCountryTextField, new Connect()));
    }
}
