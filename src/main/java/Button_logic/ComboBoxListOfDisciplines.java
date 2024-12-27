package Button_logic;
import Connect_DB.Connect;
import GUI.MainWindow;
import javafx.scene.control.ComboBox;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComboBoxListOfDisciplines {
    private ComboBox<String> comboBox;


    public ComboBoxListOfDisciplines(ComboBox<String> comboBox) {

        this.comboBox = comboBox;

    }
    public void loadDataFromDB(Connect connect){
        try {
            Statement statement = connect.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name_discipline FROM discipline");
            while (resultSet.next()) {
                comboBox.getItems().add(resultSet.getString("name_discipline"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
