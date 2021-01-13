package dk.dsg.BLL;

import dk.dsg.BE.Movie;
import dk.dsg.BE.MovieCat;
import dk.dsg.DAL.MovieCatDAO;

import java.util.List;

public class MovieCatManager {
    private final MovieCatDAO movieCatDAO;

    public MovieCatManager() {
        movieCatDAO = new MovieCatDAO();
    }

    public List<MovieCat> getAllMovieCats() {
        return movieCatDAO.getAllMovieCat();
    }

    public void addMovieCat(MovieCat movieCat) {
        movieCatDAO.addMovieCat(movieCat);
    }

    public void updateMovieCat(MovieCat movieCat) {
        movieCatDAO.updateMovieCat(movieCat);
    }

    public void deleteMovieCat(MovieCat movieCat) {
        movieCatDAO.deleteMovieCat(movieCat);
    }
}
