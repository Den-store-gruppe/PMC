package dk.dsg.DAL;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
import dk.dsg.BE.Movie;


import java.sql.*;
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

    public void addMovie(Movie movie) {
        String query = "INSERT INTO Movie(id, movieName, rating, filePath, lastView) VALUES (?,?,?,?,?)";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movie.getID());
            preparedStatement.setString(2, movie.getMovieName());
            preparedStatement.setInt(3, movie.getRating());
            preparedStatement.setString(4, movie.getFilePath());
            preparedStatement.setDate(5, (Date) movie.getLastView());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMovie(Movie movie) {
        String query = "UPDATE Movie SET movieName = ?, rating = ?, filePath = ?, lastView = ? WHERE id = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getRating());
            preparedStatement.setString(3, movie.getFilePath());
            preparedStatement.setInt(4, movie.getID());
            preparedStatement.setDate(5, (Date) movie.getLastView());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovie(Movie movie) {
        String query = "DELETE from Movie WHERE movieName = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
