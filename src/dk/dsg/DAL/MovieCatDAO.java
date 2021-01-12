package dk.dsg.DAL;

import dk.dsg.BE.MovieCat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieCatDAO {
    private DatabaseDAO databaseConnector;

    public MovieCatDAO() {
        databaseConnector = new DatabaseDAO();
    }

    /***
     * Gives a List filled with all the recorded movie-categories
     * @return A list of all records in MovieCat
     * @see Connection
     * @see PreparedStatement
     * @see MovieCat
     * @see List
     */
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

    /***
     * Inserts a new record of a new MovieCat into the Database
     * @param movieCat
     * @see MovieCat
     * @see Connection
     * @see PreparedStatement
     */
    public void addMovieCat(MovieCat movieCat) {
        String query = "INSERT INTO MovieCat(categoryId, movieId) VALUES (?,?)";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movieCat.getCategoryId());
            preparedStatement.setInt(2, movieCat.getMovieId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

    public void updateMovieCat(MovieCat movieCat) {
        String query = "UPDATE MovieCat SET categoryId = ?, movieId = ?, WHERE id = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movieCat.getCategoryId());
            preparedStatement.setInt(2, movieCat.getMovieId());
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMovieCat(MovieCat movieCat) {
        String query = "DELETE from MovieCat WHERE id = ?";
        try(Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movieCat.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
