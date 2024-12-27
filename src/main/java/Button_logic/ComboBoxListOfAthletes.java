package Button_logic;

import Connect_DB.Connect;
import GUI.MainWindow;
import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComboBoxListOfAthletes {
    private ComboBox<String> comboBox;

    public ComboBoxListOfAthletes(ComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }

    public void loadDataFromDB(Connect connect){
        try {
            Statement statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select concat(first_name,' ',name_athlete,' ',middle_name) as full_name from athlete");
            while (resultSet.next()){
                comboBox.getItems().add(resultSet.getString("full_name"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
