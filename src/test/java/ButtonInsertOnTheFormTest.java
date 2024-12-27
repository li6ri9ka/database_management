import Button_logic.ButtonInsertOnTheForm;
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

class ButtonInsertOnTheFormTest {

    @Test
    void testAddAthlete() throws SQLException {

        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_athlete")).thenReturn(1);

        ButtonInsertOnTheForm buttonInsert = new ButtonInsertOnTheForm(null, null, null, null, null, null, null, null, connect);

        int athleteId = buttonInsert.addAthlete("Иванов", "Иван", "Иванович");

        assertEquals(1, athleteId, "ID спортсмена должен быть равен 1");

        verify(preparedStatement).setString(1, "Иванов");
        verify(preparedStatement).setString(2, "Иван");
        verify(preparedStatement).setString(3, "Иванович");
    }

    @Test
    void testAddAthleteThrowsException() throws SQLException {

        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(new SQLException("Ошибка при выполнении запроса"));

        ButtonInsertOnTheForm buttonInsert = new ButtonInsertOnTheForm(null, null, null, null, null, null, null, null, connect);

        assertThrows(SQLException.class, () -> {
            buttonInsert.addAthlete("Иванов", "Иван", "Иванович");
        }, "Должно быть выброшено исключение при ошибке SQL");
    }

    @Test
    void testGetWeightCategoryId() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_weight_category")).thenReturn(2);

        ButtonInsertOnTheForm buttonInsert = new ButtonInsertOnTheForm(null, null, null, null, null, null, null, null, connect);

        int weightCategoryId = buttonInsert.getWeightCategoryId(75.5);

        assertEquals(2, weightCategoryId, "ID весовой категории должен быть равен 2");

        verify(preparedStatement).setDouble(1, 75.5);
    }

    @Test
    void testGetCountyId() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_country")).thenReturn(3);

        ButtonInsertOnTheForm buttonInsert = new ButtonInsertOnTheForm(null, null, null, null, null, null, null, null, connect);

        int countryId = buttonInsert.getCountyId("Россия");

        assertEquals(3, countryId, "ID страны должен быть равен 3");

        verify(preparedStatement).setString(1, "Россия");
    }

    @Test
    void testAddRating() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        ButtonInsertOnTheForm buttonInsert = new ButtonInsertOnTheForm(null, null, null, null, null, null, null, null, connect);

        buttonInsert.addRating(1, 2, 3, 4);

        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setInt(2, 2);
        verify(preparedStatement).setInt(3, 3);
        verify(preparedStatement).setInt(4, 4);
        verify(preparedStatement).executeUpdate();
    }
}