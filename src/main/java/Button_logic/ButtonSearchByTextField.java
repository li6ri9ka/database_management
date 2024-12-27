package Button_logic;

import Connect_DB.Connect;
import GUI.MainWindow;
import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ButtonSearchByTextField implements EventHandler<ActionEvent> {
    private TextField field;
    private Connect connect;
    private MainWindow mainWindow;
    public ButtonSearchByTextField(TextField field, Connect connect, MainWindow mainWindow) {
        this.field = field;
        this.connect = connect;
        this.mainWindow = mainWindow;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Statement statement = connect.getConnection().createStatement();
            String inputText = field.getText();
            String query = "select\t\n" +
                    "\tconcat(first_name,' ',name_athlete,' ',middle_name) as \"FullName\",\n" +
                    "\tname_discipline as \"Discipline\",\n" +
                    "\tweight_category as \"Weight_category\",\n" +
                    "\tcountry as \"Country\"\n" +
                    "from rating\n" +
                    "\tinner join athlete on  \n" +
                    "\t \trating.athlete_id = athlete.id_athlete\n" +
                    "\tinner join discipline on\n" +
                    "\t \trating.discipline_id = discipline.id_discipline\n" +
                    "\tinner join weight_category on\n" +
                    "\t\trating.weight_category_id = weight_category.id_weight_category\n" +
                    "\tinner join country on\n" +
                    "\t\trating.country_id =\tcountry.id_country\n" +
                    "\t\twhere name_discipline like ? or concat(first_name,' ',name_athlete,' ',middle_name) like ?\n";

            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query);
            preparedStatement.setString(1,"%"+inputText+"%");
            preparedStatement.setString(2,"%"+inputText+"%");


            mainWindow.clearTextListView();
            mainWindow.addTextListView("FullName" + " \t| \t" + "Discipline" + " \t|\t " + "Weight_category" + " \t|\t " + "Country");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String fullName = resultSet.getString("FullName");
                String discipline = resultSet.getString("Discipline");
                String weightCategory = resultSet.getString("Weight_category");
                String country = resultSet.getString("Country");
                mainWindow.addTextListView(resultSet.getRow()+". " + fullName+" | "+ discipline+" | "+ weightCategory+" | "+ country);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
