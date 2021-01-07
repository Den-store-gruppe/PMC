package dk.dsg.gui.model;

import dk.dsg.BE.Movie;
import dk.dsg.BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MovieModel {

    private ObservableList<Movie> allMovies = FXCollections.observableArrayList();

    private final MovieManager movieManager;

    public MovieModel() {
        movieManager = new MovieManager();
    }

    public ObservableList<Movie> getAllMovies() throws SQLException {
        allMovies = FXCollections.observableArrayList();
        allMovies.addAll(movieManager.getAllMovies());
        return allMovies;
    }

    public void addMovie(Movie movie) {
        movieManager.addMovie(movie);
    }

    public void updateMovie(Movie movie) throws SQLException {
        movieManager.updateMovie(movie);
        allMovies.add(movie);
        allMovies.clear();
        allMovies.addAll(movieManager.getAllMovies());
    }

    public void deleteMovie(Movie movie) {
        movieManager.deleteMovie(movie);
    }
}
