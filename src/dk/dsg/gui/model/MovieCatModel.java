package dk.dsg.gui.model;

import dk.dsg.BE.Movie;
import dk.dsg.BE.MovieCat;
import dk.dsg.BLL.MovieCatManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MovieCatModel {
    private ObservableList<MovieCat> allMovieCats = FXCollections.observableArrayList();

    private final MovieCatManager movieCatManager;

    public MovieCatModel() {
        movieCatManager = new MovieCatManager();
    }

    /***
     * Puts all the movies with a category in a ObservableList
     * @return an list of all the movies with a category
     */
    public ObservableList<MovieCat> getAllMovieCats() {
        allMovieCats = FXCollections.observableArrayList();
        allMovieCats.addAll(movieCatManager.getAllMovieCats());
        return allMovieCats;
    }

    /***
     * Adds a movie with a category
     * @param movieCat
     */
    public void addMovieCat(MovieCat movieCat) {
        movieCatManager.addMovieCat(movieCat);
    }

    /***
     * Updates a movie with a category
     * @param movieCat
     */
    public void updateMovieCat(MovieCat movieCat) {
        movieCatManager.updateMovieCat(movieCat);
        allMovieCats.clear();
        allMovieCats.addAll(movieCatManager.getAllMovieCats());
    }

    /***
     * Deletes a movie with a category
     * @param movie
     */
    public void deleteMovieCat(Movie movie){
        movieCatManager.deleteMovieCat(new MovieCat(-1,-1, movie.getID()));
    }
}
