package dk.dsg.DAL;

import dk.dsg.BE.Category;
import dk.dsg.BE.Movie;
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
     * Gives a list filled with categories which belong to a movie
     * @param movie
     * @return List with categories
     */
    public List<Category> getCategoriesByMovie(Movie movie){
        ArrayList<Category> tmp = new ArrayList<>();
        String query = "SELECT Category.catName, Category.id FROM Category, MovieCat WHERE MovieCat.movieId = ? AND MovieCat.categoryId = Category.id";

        try(Connection connection = databaseConnector.getConnection()){
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, movie.getID());
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()){
                tmp.add(new Category(
                        resultSet.getInt("id"),
                        resultSet.getString("catName")
                ));
            }

            return tmp;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return tmp;
    }

    /***
     * Inserts a new record of a new MovieCat into the Database
     * @param movieCat
     * @see MovieCat
     * @see Connection
     * @see PreparedStatement
     */
    public void addMovieCat(MovieCat movieCat) {
        String query = "INSERT INTO MovieCat (categoryId, movieId) VALUES (?,?)";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movieCat.getCategoryId());
            preparedStatement.setInt(2, movieCat.getMovieId());
            preparedStatement.execute();
        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

    /***
     * Updates a given MovieCat to the specified values.
     * @param movie the movie with categories that needs to be updated in the database
     * @see MovieCat
     */
    public void updateMovieCat(Movie movie) {
        deleteMovieCat(new MovieCat(-1,-1, movie.getID()));
        for (Category c : movie.getCategories()){
            addMovieCat(new MovieCat(-1,c.getID(),movie.getID()));
        }
    }

    /***
     * Deletes a MovieCat from the database
     * @param movieCat the MovieCat to be deleted
     * @see MovieCat
     */
    public void deleteMovieCat(MovieCat movieCat) {
        String query = "DELETE FROM MovieCat WHERE movieId = ?";
        try(Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, movieCat.getMovieId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
