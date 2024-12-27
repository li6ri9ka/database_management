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

public class ButtonInsertOnTheForm implements EventHandler<ActionEvent> {
    private MainWindow mainWindow;
    private Stage primaryStage;
    private Connect connect;
    private TextField firstNameTextField;
    private TextField nameTextField;
    private TextField middleNameTextField;
    private TextField DisciplineTextField;
    private TextField WeightCategoryTextField;
    private TextField CountryTextField;

    public ButtonInsertOnTheForm(MainWindow mainWindow, Stage primaryStage, TextField firstNameTextField,TextField nameTextField, TextField middleNameTextField, TextField DisciplineTextField, TextField WeightCategoryTextField, TextField CountryTextField, Connect connect) {
        this.mainWindow = mainWindow;
        this.primaryStage = primaryStage;
        this.firstNameTextField = firstNameTextField;
        this.nameTextField = nameTextField;
        this.middleNameTextField = middleNameTextField;
        this.DisciplineTextField = DisciplineTextField;
        this.WeightCategoryTextField = WeightCategoryTextField;
        this.CountryTextField = CountryTextField;
        this.connect = connect;
    }

    @Override
    public void handle(ActionEvent event){
        String firstName = firstNameTextField.getText();
        String name = nameTextField.getText();
        String middleName = middleNameTextField.getText();
        String discipline = DisciplineTextField.getText();
        double weightCategory = Double.parseDouble(WeightCategoryTextField.getText());
        String country = CountryTextField.getText();
        try {
            addData(firstName, name, middleName,discipline,weightCategory,country);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        mainWindow.start(primaryStage);
    }



    public int addAthlete(String fistName, String name, String middleName) throws SQLException{
        String queru = "insert into athlete(first_name, name_athlete, middle_name) values(?,?,?) returning id_athlete";
        try(PreparedStatement preparedStatement =connect.getConnection().prepareStatement(queru)){
            preparedStatement.setString(1, fistName);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, middleName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id_athlete");
            }
        }
        throw new SQLException("Не удалось добавить спортсмена");
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

    public void addRating(int athleteId, int disciplineId, int weightCategoryId, int countryId) throws SQLException{
        String query = "insert into rating(athlete_id, discipline_id, weight_category_id, country_id) values(?,?,?,?)";
        try(PreparedStatement preparedStatement =connect.getConnection().prepareStatement(query)){
            preparedStatement.setInt(1, athleteId);
            preparedStatement.setInt(2, disciplineId);
            preparedStatement.setInt(3, weightCategoryId);
            preparedStatement.setInt(4, countryId);
            preparedStatement.executeUpdate();
        }
    }

    public void addData(String firstname, String name, String middleName,String discipline, double weightCategory, String country ) throws SQLException{
        try {
            int athleteId = addAthlete(firstname, name, middleName);

            int disciplineId = getDisciplineId(discipline);
            int weightCategoryId = getWeightCategoryId(weightCategory);
            int countryId = getCountyId(country);

            addRating(athleteId, disciplineId, weightCategoryId, countryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
