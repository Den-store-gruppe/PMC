package dk.dsg.DAL;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;
import dk.dsg.BE.Category;
import dk.dsg.BE.Movie;
import dk.dsg.BE.MovieCat;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    MovieCatDAO mcd;
    private final DatabaseDAO databaseConnector;
    public MovieDAO() {
        databaseConnector = new DatabaseDAO();
        mcd = new MovieCatDAO();
    }

    /***
     * Gets all the movies from the database and returns them as a list
     * @return a list containing all the movies
     * @throws SQLException
     * @see Connection
     * @see List
     * @see Movie
     */
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

    /***
     * Inserts a new movie into the database. Done through a
     * prepared statement to make the action secure from
     * sql-injections. After inserting a movie, the resultset
     * will get pulled, and MovieCat objects will be pushed
     * onto the database.
     * @param movie the movie with the information we need
     * @see Movie
     * @see PreparedStatement
     * @see Connection
     */
    public void addMovie(Movie movie) {
        String query = "INSERT INTO Movie(movieName, rating, filePath, lastView) VALUES (?,?,?,?)";
        try (Connection connection = databaseConnector.getConnection()) {
            //insert into the db
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getRating());
            preparedStatement.setString(3, movie.getFilePath());
            preparedStatement.setDate(4, movie.getLastView());
            preparedStatement.execute();

            //Extract the newly created records info
            ResultSet set = preparedStatement.getGeneratedKeys();
            if(set.next()){
                long id = set.getLong(1);
                movie.setId((int) id);
            }

            //Create a MovieCat object and let MovieCatDAO handle the insertion
            for(Category c : movie.getCategories()){
                MovieCat tmp = new MovieCat(-1,c.getID(),movie.getID());
                mcd.addMovieCat(tmp);
            }

        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

    /***
     * Updated the database to reflect changes made.
     * @param movie An altered version of the movie we want altered.
     * @see PreparedStatement
     * @see Movie
     * @see Connection
     */
    public void updateMovie(Movie movie) {
        String query = "UPDATE Movie SET movieName = ?, rating = ?, filePath = ?, lastView = ? WHERE id = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.setInt(2, movie.getRating());
            preparedStatement.setString(3, movie.getFilePath());
            preparedStatement.setDate(4, movie.getLastView());
            preparedStatement.setInt(5, movie.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

    /***
     * Deletes a movie from the database with matching information
     * as the movie given.
     * @param movie the movie the user want to be deleted
     * @see PreparedStatement
     * @see Connection
     */
    public void deleteMovie(Movie movie) {
        String query = "DELETE from Movie WHERE movieName = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movie.getMovieName());
            preparedStatement.execute();
        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }
}
