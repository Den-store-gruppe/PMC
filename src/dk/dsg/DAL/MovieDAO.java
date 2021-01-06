package dk.dsg.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import dk.dsg.BE.Movie;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    private final DatabaseDAO databaseConnector;
    public MovieDAO() {
        databaseConnector = new DatabaseDAO();
    }

    public List<Movie> getAllMovies() throws SQLException {
        Connection connection = databaseConnector.getConnection();
        List<Movie> movies = new ArrayList<>();

        String query = "SELECT * FROM Movie";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            movies.add(new Movie(
                    resultSet.getInt("id"),
                    resultSet.getString("movieName"),
                    resultSet.getInt("rating"),
                    resultSet.getString("filePath"),
                    resultSet.getDate("lastView")
            ));
        }
        connection.close();
        statement.close();
        resultSet.close();

        return movies;
    }
}
