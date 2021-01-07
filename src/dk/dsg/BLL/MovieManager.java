package dk.dsg.BLL;

import dk.dsg.BE.Movie;
import dk.dsg.DAL.MovieDAO;

import java.sql.SQLException;
import java.util.List;

public class MovieManager {
    private MovieDAO movieDAO;

    public MovieManager() throws Exception {
        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies() throws SQLException {
        return movieDAO.getAllMovies();
    }

    public void addMovie(Movie movie) {
        movieDAO.addMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieDAO.updateMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieDAO.deleteMovie(movie);
    }
}
