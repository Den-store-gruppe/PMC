package dk.dsg.DAL;

import dk.dsg.BE.MovieCat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieCatDAO {
    private DatabaseDAO databaseConnector;

    public MovieCatDAO() {
        databaseConnector = new DatabaseDAO();
    }

    public List<MovieCat> getAllMovieCat() {
        ArrayList<MovieCat> allMovieCats = new ArrayList<>();
        String query = "SELECT * FROM MovieCat";
        try (Connection connection = databaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                allMovieCats.add(new MovieCat(
                        resultSet.getInt("id"),
                        resultSet.getInt("categoryId"),
                        resultSet.getInt("movieId")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allMovieCats;
    }
}
