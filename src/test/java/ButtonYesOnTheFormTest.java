import Button_logic.ButtonYesOnTheForm;
import Connect_DB.Connect;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ButtonYesOnTheFormTest {

    @Test
    void testGetAthleteId() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_athlete")).thenReturn(1);

        ButtonYesOnTheForm buttonYes = new ButtonYesOnTheForm(null, connect, null);

        int athleteId = buttonYes.getAthleteId(new String[]{"", "Иванов"});

        assertEquals(1, athleteId, "ID спортсмена должен быть равен 1");

        verify(preparedStatement).setString(1, "Иванов");
    }

    @Test
    void testGetAthleteIdThrowsException() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(new SQLException("Ошибка при выполнении запроса"));

        ButtonYesOnTheForm buttonYes = new ButtonYesOnTheForm(null, connect, null);

        assertThrows(SQLException.class, () -> {
            buttonYes.getAthleteId(new String[]{"", "Иванов"});
        }, "Должно быть выброшено исключение при ошибке SQL");
    }

    @Test
    void testGetRatingId() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_rating")).thenReturn(2);

        ButtonYesOnTheForm buttonYes = new ButtonYesOnTheForm(null, connect, null);

        int ratingId = buttonYes.getRatingId(1);

        assertEquals(2, ratingId, "ID рейтинга должен быть равен 2");

        verify(preparedStatement).setInt(1, 1);
    }

    @Test
    void testDeleteRating() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        ButtonYesOnTheForm buttonYes = new ButtonYesOnTheForm(null, connect, null);

        buttonYes.deleteRating(1);

        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDeleteAthlete() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        ButtonYesOnTheForm buttonYes = new ButtonYesOnTheForm(null, connect, null);

        buttonYes.deleteAthlete(1);

        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).executeUpdate();
    }
}