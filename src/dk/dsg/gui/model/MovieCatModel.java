package dk.dsg.gui.model;

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

    public ObservableList<MovieCat> getAllMovieCats() {
        allMovieCats = FXCollections.observableArrayList();
        allMovieCats.addAll(movieCatManager.getAllMovieCats());
        return allMovieCats;
    }

    public void addMovieCat(MovieCat movieCat) {
        movieCatManager.addMovieCat(movieCat);
    }

    public void updateMovieCat(MovieCat movieCat) {
        movieCatManager.updateMovieCat(movieCat);
        allMovieCats.clear();
        allMovieCats.addAll(movieCatManager.getAllMovieCats());
    }

}
