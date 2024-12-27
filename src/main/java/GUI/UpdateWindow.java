package GUI;

import Button_logic.ButtonUpdate;
import Button_logic.ButtonUpdateOnTheForm;
import Connect_DB.Connect;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateWindow extends Application {
    private Stage primaryStage;
    private ListView<String> listView;
    private Connect connect;


    public UpdateWindow(ListView<String> listView, Stage primaryStage, Connect connect) {
        this.listView = listView;
        this.primaryStage = primaryStage;
        this.connect = connect;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane updateWindow = new AnchorPane();
        Scene scene = new Scene(updateWindow, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Window");

        updateWindow.setStyle("-fx-background-color: #323232");

        Label updateNameLabel = new Label("Введите имя: ");
        Label updateFirstNameLabel = new Label("Введите фамилию: ");
        Label updateMiddleNameLabel = new Label("Введите отчество: ");
        Label updateDisciplineLabel = new Label("Введите дисциплину: ");
        Label updateWeightCategoryLabel = new Label("Введите весовую категорию:");
        Label updateCountryLabel = new Label("Введите страну: ");

        TextField updateNameTextField = new TextField();
        TextField updateFirstNameTextField = new TextField();
        TextField updateMiddleNameTextField = new TextField();
        TextField updateDisciplineTextField = new TextField();
        TextField updateWeightCategoryTextField = new TextField();
        TextField updateCountryTextField = new TextField();

        Button updateButton = new Button("Обновить");

        updateNameLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        updateFirstNameLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        updateMiddleNameLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        updateDisciplineLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        updateWeightCategoryLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");
        updateCountryLabel.setStyle("-fx-text-fill: white; -fx-font-family: 'Times New Roman'; -fx-font-size: 30");


        updateNameTextField.setStyle("-fx-font-size: 20");
        updateFirstNameTextField.setStyle("-fx-font-size: 20");
        updateMiddleNameTextField.setStyle("-fx-font-size: 20");
        updateDisciplineTextField.setStyle("-fx-font-size: 20");
        updateWeightCategoryTextField.setStyle("-fx-font-size: 20");
        updateCountryTextField.setStyle("-fx-font-size: 20");

        updateFirstNameTextField.setPrefSize(300, 40);
        updateNameTextField.setPrefSize(300, 40);
        updateMiddleNameTextField.setPrefSize(300, 40);
        updateDisciplineTextField.setPrefSize(300, 40);
        updateWeightCategoryTextField.setPrefSize(300, 40);
        updateCountryTextField.setPrefSize(300, 40);

        updateButton.setPrefSize(250, 80);

        AnchorPane.setTopAnchor(updateFirstNameLabel, 50.0);
        AnchorPane.setLeftAnchor(updateFirstNameLabel, 50.0);

        AnchorPane.setTopAnchor(updateNameLabel, 120.0);
        AnchorPane.setLeftAnchor(updateNameLabel, 50.0);

        AnchorPane.setTopAnchor(updateMiddleNameLabel, 190.0);
        AnchorPane.setLeftAnchor(updateMiddleNameLabel, 50.0);

        AnchorPane.setTopAnchor(updateDisciplineLabel, 260.0);
        AnchorPane.setLeftAnchor(updateDisciplineLabel, 50.0);

        AnchorPane.setTopAnchor(updateWeightCategoryLabel, 330.0);
        AnchorPane.setLeftAnchor(updateWeightCategoryLabel, 50.0);

        AnchorPane.setTopAnchor(updateCountryLabel, 400.0);
        AnchorPane.setLeftAnchor(updateCountryLabel, 50.0);

        AnchorPane.setTopAnchor(updateFirstNameTextField, 50.0);
        AnchorPane.setLeftAnchor(updateFirstNameTextField, 320.0);

        AnchorPane.setTopAnchor(updateNameTextField, 120.0);
        AnchorPane.setLeftAnchor(updateNameTextField, 250.0);

        AnchorPane.setTopAnchor(updateMiddleNameTextField, 190.0);
        AnchorPane.setLeftAnchor(updateMiddleNameTextField, 315.0);

        AnchorPane.setTopAnchor(updateDisciplineTextField, 260.0);
        AnchorPane.setLeftAnchor(updateDisciplineTextField, 350.0);

        AnchorPane.setTopAnchor(updateWeightCategoryTextField, 330.0);
        AnchorPane.setLeftAnchor(updateWeightCategoryTextField, 440.0);

        AnchorPane.setTopAnchor(updateCountryTextField, 400.0);
        AnchorPane.setLeftAnchor(updateCountryTextField, 280.0);

        AnchorPane.setTopAnchor(updateButton, 480.0);
        AnchorPane.setLeftAnchor(updateButton, 260.0);

        updateWindow.getChildren().addAll(updateNameLabel, updateFirstNameLabel, updateMiddleNameLabel, updateDisciplineLabel, updateWeightCategoryLabel, updateCountryLabel,
                updateFirstNameTextField, updateNameTextField, updateMiddleNameTextField, updateDisciplineTextField, updateWeightCategoryTextField, updateCountryTextField, updateButton);






        String selectItem = listView.getSelectionModel().getSelectedItem();

        if (selectItem != null) {
            String[] parts = selectItem.split("\\s*\\|\\s*");
            String[] fullName = parts[0].split(" ");
            updateFirstNameTextField.setText(fullName[1]);
            updateNameTextField.setText(fullName[2]);
            updateMiddleNameTextField.setText(fullName[3]);
            updateDisciplineTextField.setText(parts[1]);
            updateWeightCategoryTextField.setText(parts[2]);
            updateCountryTextField.setText(parts[3]);
        }

        int athleteId = getAthleteId(updateFirstNameTextField.getText(),updateNameTextField.getText(),updateMiddleNameTextField.getText());
        updateButton.setOnAction(new ButtonUpdateOnTheForm(primaryStage, new Connect(), updateFirstNameTextField, updateNameTextField, updateMiddleNameTextField, updateDisciplineTextField,updateWeightCategoryTextField,updateCountryTextField, athleteId));
    }
    public int getAthleteId(String firstName, String name, String middleName) throws SQLException {
        String query = "SELECT id_athlete FROM athlete WHERE first_name = ? AND name_athlete = ? AND middle_name = ?";
        try(PreparedStatement statement = connect.getConnection().prepareStatement(query)) {
            statement.setString(1, firstName);
            statement.setString(2,name);
            statement.setString(3, middleName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_athlete");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        throw new SQLException("Спортик не найден");
    }
}
