package GUI;

import Connect_DB.Connect;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import Button_logic.*;

import java.sql.SQLException;

public class MainWindow extends Application {
    private ListView<String> infoListView;
    private Connect connect;

    public ListView<String> getinfoListView() {
        return infoListView;
    }

    public void setInfoLabel(ListView<String> infoListView) {
        this.infoListView = infoListView;
    }

    public void addTextListView(String text) {

        if (!infoListView.getItems().contains(text)) {
            infoListView.getItems().add(text);

        }
    }

    public void clearTextListView() {
        infoListView.getItems().clear();
    }



    @Override
    public void start(Stage primaryStage) {
        try {
            connect = new Connect();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения");

            return;
        }

        AnchorPane mainPane = new AnchorPane();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        Scene scene = new Scene(mainPane, bounds.getWidth(), bounds.getHeight());


        Button buttonSelectInfo = new Button("Вывод информации");
        Button createButton = new Button("Создать");
        Button updateButton = new Button("Обновить информацию");
        Button deleteButton = new Button("Удалить");
        Button exitButton = new Button("Выход");
        Button buttonSearch = new Button("\uD83D\uDD0D");
        infoListView = new ListView<>();
        ComboBox<String> listOfDisciplines = new ComboBox<>();
        ComboBox<String> listOfAthletes = new ComboBox<>();
        TextField textField = new TextField();
        textField.setPromptText("Поиск...");



        listOfDisciplines.setPromptText("Выберите дисциплину");
        listOfAthletes.setPromptText("Выберите спортсмена");



        buttonSelectInfo.setPrefSize(300, 107);
        createButton.setPrefSize(300, 107);
        updateButton.setPrefSize(300, 107);
        deleteButton.setPrefSize(300, 107);
        infoListView.setPrefSize(1570, 800);
        listOfDisciplines.setPrefSize(300, 10);
        listOfAthletes.setPrefSize(300, 10);
        exitButton.setPrefSize(60, 30);
        textField.setPrefSize(700, 30);
        buttonSearch.setPrefSize(38.3 ,38.3);



        AnchorPane.setTopAnchor(buttonSelectInfo, 950.0);
        AnchorPane.setLeftAnchor(buttonSelectInfo, 80.0);

        AnchorPane.setTopAnchor(createButton, 950.0);
        AnchorPane.setLeftAnchor(createButton, 500.0);

        AnchorPane.setTopAnchor(updateButton, 950.0);
        AnchorPane.setLeftAnchor(updateButton, 930.0);

        AnchorPane.setTopAnchor(deleteButton, 950.0);
        AnchorPane.setRightAnchor(deleteButton, 80.0);

        AnchorPane.setTopAnchor(infoListView, 90.0);
        AnchorPane.setLeftAnchor(infoListView, 80.0);

        AnchorPane.setTopAnchor(listOfDisciplines, 30.0);
        AnchorPane.setLeftAnchor(listOfDisciplines, 80.0);

        AnchorPane.setTopAnchor(listOfAthletes, 30.0);
        AnchorPane.setLeftAnchor(listOfAthletes, 500.0);

        AnchorPane.setTopAnchor(textField, 30.0);
        AnchorPane.setRightAnchor(textField, 80.0);

        AnchorPane.setTopAnchor(buttonSearch, 30.0);
        AnchorPane.setRightAnchor(buttonSearch, 80.0);



        AnchorPane.setRightAnchor(exitButton, 0.00);


        infoListView.setStyle("-fx-background-color: #393939; -fx-text-fill: white; -fx-font-size: 20;-fx-alignment: top-left;");
        textField.setStyle("-fx-font-size: 20");
        mainPane.setStyle("-fx-background-color: #323232");
        listOfDisciplines.setStyle("-fx-font-size: 20");

        listOfAthletes.setStyle("-fx-font-size: 20");



        mainPane.getChildren().addAll(buttonSelectInfo, createButton, updateButton, deleteButton, infoListView, listOfDisciplines, textField,listOfAthletes, exitButton,buttonSearch);

        exitButton.setOnAction(new Exit(primaryStage));
        deleteButton.setOnAction(new ButtonDeletion(primaryStage,infoListView));
        buttonSelectInfo.setOnAction(new ButtonSelectInfo(connect, this,listOfDisciplines,listOfAthletes));
        ComboBoxListOfDisciplines comboBoxListOfDisciplines = new ComboBoxListOfDisciplines(listOfDisciplines);
        comboBoxListOfDisciplines.loadDataFromDB(connect);
        ComboBoxListOfAthletes comboBoxListOfAthletes = new ComboBoxListOfAthletes(listOfAthletes);
        comboBoxListOfAthletes.loadDataFromDB(connect);
        buttonSearch.setOnAction(new ButtonSearchByTextField(textField,connect,this));
        createButton.setOnAction(new ButtonInsert(primaryStage));
        updateButton.setOnAction(new ButtonUpdate(primaryStage,infoListView));



        primaryStage.setScene(scene);
        primaryStage.setTitle("Database_manager");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

}

