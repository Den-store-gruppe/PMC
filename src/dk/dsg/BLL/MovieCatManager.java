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

    /***
     * Adds all movies with a category to the list.
     * @return a list of movies with a category.
     */
    public List<MovieCat> getAllMovieCats() {
        return movieCatDAO.getAllMovieCat();
    }

    /***
     * Adds a movie with a category to the database.
     * @param movieCat
     */
    public void addMovieCat(MovieCat movieCat) {
        movieCatDAO.addMovieCat(movieCat);
    }

    /***
     * Updates a movie with a category in the database.
     * @param movieCat
     */
    public void updateMovieCat(MovieCat movieCat) {
        movieCatDAO.updateMovieCat(movieCat);
    }

    /***
     * Deletes a movie with a category from the database.
     * @param movieCat
     */
    public void deleteMovieCat(MovieCat movieCat) {
        movieCatDAO.deleteMovieCat(movieCat);
    }
}
