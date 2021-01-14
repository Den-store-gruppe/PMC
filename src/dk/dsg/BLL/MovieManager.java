package dk.dsg.BLL;

import dk.dsg.BE.Movie;
import dk.dsg.BLL.util.MovieFilter;
import dk.dsg.DAL.MovieDAO;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class MovieManager {
    private final MovieDAO movieDAO;
    private final MovieFilter movieFilter;

    public MovieManager() {
        movieDAO = new MovieDAO();
        movieFilter = new MovieFilter();
    }

    /***
     * Calls the MovieDAO method with the provided movie
     * @return The list of all movies in the database
     */
    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.getAllMovies();
    }


    /***
     * Calls the MovieDAO method with the provided movie
     * @param movie The movie to be added to the database
     */
    public void addMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    /***
     * Calls the MovieDAO method with the provided movie
     * @param movie The altered movie to be updated
     */
    public void updateMovie(Movie movie) {
        movieDAO.updateMovie(movie);
    }

    /***
     * Calls the MovieDAO method with the provided movie
     * @param movie the movie to be deleted
     */
    public void deleteMovie(Movie movie) {
        movieDAO.deleteMovie(movie);
    }

    public ObservableList<Movie> searchMovie(ObservableList<Movie> movies, String search) {
        return movieFilter.search(movies, search);
    }
}
