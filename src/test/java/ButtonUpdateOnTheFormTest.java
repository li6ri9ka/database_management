import Button_logic.ButtonUpdateOnTheForm;
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

class ButtonUpdateOnTheFormTest {

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
        when(resultSet.getInt("id_weight_category")).thenReturn(1);

        ButtonUpdateOnTheForm buttonUpdate = new ButtonUpdateOnTheForm(null, connect, null, null, null, null, null, null, 0);

        int weightCategoryId = buttonUpdate.getWeightCategoryId(75.5);

        assertEquals(1, weightCategoryId, "ID весовой категории должен быть равен 1");

        verify(preparedStatement).setDouble(1, 75.5);
    }

    @Test
    void testGetWeightCategoryIdThrowsException() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenThrow(new SQLException("Ошибка при выполнении запроса"));

        ButtonUpdateOnTheForm buttonUpdate = new ButtonUpdateOnTheForm(null, connect, null, null, null, null, null, null, 0);

        assertThrows(SQLException.class, () -> {
            buttonUpdate.getWeightCategoryId(75.5);
        }, "Должно быть выброшено исключение при ошибке SQL");
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
        when(resultSet.getInt("id_country")).thenReturn(2);

        ButtonUpdateOnTheForm buttonUpdate = new ButtonUpdateOnTheForm(null, connect, null, null, null, null, null, null, 0);

        int countryId = buttonUpdate.getCountyId("Россия");

        assertEquals(2, countryId, "ID страны должен быть равен 2");

        verify(preparedStatement).setString(1, "Россия");
    }

    @Test
    void testGetDisciplineId() throws SQLException {
        Connect connect = mock(Connect.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(connect.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("id_discipline")).thenReturn(3);

        ButtonUpdateOnTheForm buttonUpdate = new ButtonUpdateOnTheForm(null, connect, null, null, null, null, null, null, 0);

        int disciplineId = buttonUpdate.getDisciplineId("Бег");

        assertEquals(3, disciplineId, "ID дисциплины должен быть равен 3");

        verify(preparedStatement).setString(1, "Бег");
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
        when(resultSet.getInt("id_rating")).thenReturn(4);

        ButtonUpdateOnTheForm buttonUpdate = new ButtonUpdateOnTheForm(null, connect, null, null, null, null, null, null, 0);

        int ratingId = buttonUpdate.getRatingId(1);

        assertEquals(4, ratingId, "ID рейтинга должен быть равен 4");

        verify(preparedStatement).setInt(1, 1);
    }
}