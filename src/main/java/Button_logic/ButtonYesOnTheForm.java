package Button_logic;

import Connect_DB.Connect;
import GUI.MainWindow;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;

public class ButtonYesOnTheForm implements EventHandler<ActionEvent> {
    private ListView<String> listView;
    private Connect connect;
    private Stage primaryStage;

    public ButtonYesOnTheForm(ListView<String> listView, Connect connect, Stage primaryStage) {
        this.listView = listView;
        this.connect = connect;
        this.primaryStage = primaryStage;
    }

    @Override
    public void handle(ActionEvent event) {
        String selectItem = listView.getSelectionModel().getSelectedItem();
        MainWindow mainWindow = new MainWindow();
        if (selectItem != null) {
            String[] fullName = selectItem.split(" ");
            try {
                deleteRating(getRatingId(getAthleteId(fullName)));
                deleteAthlete(getAthleteId(fullName));
                mainWindow.start(primaryStage);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getAthleteId(String[] deletionFirstName)throws SQLException {
        String firstName = deletionFirstName[1];
        String query = "select id_athlete from athlete where first_name = ?";
        try(PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, firstName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_athlete");
            }

        }
        throw new SQLException();
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

    public void deleteRating(int ratingId)throws SQLException {
        String query = "delete from rating where id_rating = ?";
        try(PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, ratingId);
            preparedStatement.executeUpdate();
        }
    }

    public void deleteAthlete(int athleteId)throws SQLException {
        String query = "delete from athlete where id_athlete = ?";
        try(PreparedStatement preparedStatement = connect.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, athleteId);
            preparedStatement.executeUpdate();
        }
    }


}
