package Button_logic;

import com.sun.tools.javac.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import Connect_DB.*;
import GUI.MainWindow;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ButtonSelectInfo implements EventHandler<ActionEvent>{
    private Connect connect;
    private MainWindow mainWindow;
    private ComboBox<String> comboBox1;
    private ComboBox<String> comboBox2;

    public ButtonSelectInfo(Connect connect, MainWindow mainWindow, ComboBox<String> comboBox1, ComboBox<String> comboBox2) {
        this.connect = connect;
        this.mainWindow = mainWindow;
        this.comboBox1 = comboBox1;
        this.comboBox2 = comboBox2;

    }

    @Override
    public void handle(ActionEvent event) {
        try {
            String selectedDiscipline = comboBox1.getValue();
            String selectedFullName = comboBox2.getValue();
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
                    "\t\trating.country_id =\tcountry.id_country\n";

            boolean hasCondition = false;
            if(selectedDiscipline != null) {
                query+= "where name_discipline = ?";
                hasCondition = true;
            }
            if (selectedFullName != null) {
                    query+= "where concat(first_name,' ',name_athlete,' ',middle_name) = ?";
                    hasCondition = true;
            }
            PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query);

            int paramIndex = 1;
            if(selectedDiscipline != null) {
                preparedStatement.setString(paramIndex, selectedDiscipline);
                paramIndex++;
            }
            if(selectedFullName != null) {
                preparedStatement.setString(paramIndex, selectedFullName);
            }
            ResultSet resultSet = preparedStatement.executeQuery();

            mainWindow.clearTextListView();
            mainWindow.addTextListView("FullName" + " \t| \t" + "Discipline" + " \t|\t " + "Weight_category" + " \t|\t " + "Country");

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
