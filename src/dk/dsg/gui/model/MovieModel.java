package dk.dsg.gui.model;

import dk.dsg.BE.Category;
import dk.dsg.BE.Movie;
import dk.dsg.BE.MovieCat;
import dk.dsg.BLL.MovieCatManager;
import dk.dsg.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MovieModel {

    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();

    private final MovieManager movieManager;
    private final MovieCatManager movieCatManager;

    public MovieModel() {
        movieManager = new MovieManager();
        movieCatManager = new MovieCatManager();
    }

    /***
     * Puts all the movies from the MovieManager and inserts them into an ObservableList
     * @return an observable list of all the movies
     * @throws SQLException
     * @see MovieManager
     */
    public ObservableList<Movie> getAllMovies() throws SQLException {
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        return allMovies;
    }

    /***
     * Adds a movie to the ObservableList
     * @param movie
     */
    public void addMovie(Movie movie) {
        movieManager.addMovie(movie);
    }

    /***
     * Updated the JavaFX TableView to show the new movie,
     * then calls the DAO to update the DB
     * @param movie
     * @throws SQLException
     * @see dk.dsg.DAL.MovieDAO
     */
    public void updateMovie(Movie movie) throws SQLException {
        movieManager.updateMovie(movie);
        movieCatManager.updateMovieCat(movie);
        allMovies.clear();
        allMovies.addAll(movieManager.getAllMovies());
    }


    /***
     * Deletes a movie from the DB
     * @param movie
     * @see dk.dsg.DAL.MovieDAO
     */
    public void deleteMovie(Movie movie) {
        movieManager.deleteMovie(movie);
    }

    public ObservableList<Movie> searchMovie (ObservableList<Movie> movies, String search) {
        return movieManager.searchMovie(movies, search);
    }
    public ObservableList<Movie> filterMovie(ObservableList<Movie> movies, Category category){
        return movieManager.filterMovie(movies, category);
    }
}
