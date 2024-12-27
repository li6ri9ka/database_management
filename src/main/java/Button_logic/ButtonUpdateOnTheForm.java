package Button_logic;

import Connect_DB.Connect;
import GUI.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ButtonUpdateOnTheForm implements EventHandler<ActionEvent> {
private Stage primaryStage;
private Connect connect;
private TextField firstName;
private TextField name;
private TextField middleName;
private TextField discipline;
private TextField weightCategory;
private TextField country;
private int athleteId;

    public ButtonUpdateOnTheForm(Stage primaryStage, Connect connect, TextField firstName, TextField name, TextField middleName, TextField discipline, TextField weightCategory, TextField country, int athleteId) {
        this.primaryStage = primaryStage;
        this.connect = connect;
        this.firstName = firstName;
        this.name = name;
        this.middleName = middleName;
        this.discipline = discipline;
        this.weightCategory = weightCategory;
        this.country = country;
        this.athleteId = athleteId;
    }

    @Override
    public void handle(ActionEvent event) {
        String getFirstName = firstName.getText();
        String getName = name.getText();
        String getMiddleName = middleName.getText();
        String getDiscipline = discipline.getText();
        String getWeightCategory = weightCategory.getText();
        String getCountry = country.getText();

        try {
            updateAthleteId(athleteId);

            int disciplineId = getDisciplineId(getDiscipline);
            int weightCategoryId = getWeightCategoryId(Double.parseDouble(getWeightCategory));
            int countryId = getCountyId(getCountry);
            int ratingId = getRatingId(athleteId);


            updateData(ratingId, athleteId, disciplineId, weightCategoryId, countryId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (NumberFormatException e) {
            System.err.println("Некорректное значение весовой категории");
        }


        MainWindow mainWindow = new MainWindow();
        mainWindow.start(primaryStage);
    }




    public int getWeightCategoryId(double weightCategory) throws SQLException{
        String query = "select id_weight_category from weight_category where weight_category = ?";
        try(PreparedStatement preparedStatement =connect.getConnection().prepareStatement(query)){
            preparedStatement.setDouble(1, weightCategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id_weight_category");
            }
        }
        throw new SQLException("Весовая категория не найдена");
    }

    public int getCountyId(String country) throws SQLException{
        String query = "select id_country from country where country = ?";
        try(PreparedStatement preparedStatement =connect.getConnection().prepareStatement(query)){
            preparedStatement.setString(1, country);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id_country");
            }
        }
        throw new SQLException("Страна не найдена");
    }

    public int getDisciplineId(String discipline) throws SQLException{
        String query = "SELECT id_discipline FROM discipline WHERE name_discipline = ?;";
        try(PreparedStatement preparedStatement =connect.getConnection().prepareStatement(query)){
            preparedStatement.setString(1, discipline);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id_discipline");
            }
        }
        throw new SQLException("Дисциплина не найдена");
    }

    public int getRatingId(int athleteId)throws SQLException {
        String query = "select id_rating from rating where athlete_id = ?";
        try(PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, athleteId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_rating");
            }
        }
        throw new SQLException();
    }
    public void updateAthleteId(int athleteId) throws SQLException {
        String query = "update athlete set first_name = ?,name_athlete = ?, middle_name = ? where id_athlete = ?";
        try(PreparedStatement preparedStatement =connect.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, firstName.getText());
            preparedStatement.setString(2, name.getText());
            preparedStatement.setString(3, middleName.getText());
            preparedStatement.setInt(4, athleteId);
            preparedStatement.executeUpdate();
        }
    }


    public void updateData(int ratingId,int athleteId, int disciplineId, int weightCategoryId, int countryId) throws SQLException {
        String query = "update rating set athlete_id = ?, discipline_id = ?, weight_category_id = ?, country_id = ? where id_rating = ?";
        try (PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, athleteId);
            preparedStatement.setInt(2, disciplineId);
            preparedStatement.setInt(3, weightCategoryId);
            preparedStatement.setInt(4, countryId);
            preparedStatement.setInt(5, ratingId);
            preparedStatement.executeUpdate();

        }
    }
}
