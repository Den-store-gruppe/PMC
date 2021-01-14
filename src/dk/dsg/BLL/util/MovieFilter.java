package dk.dsg.BLL.util;

import dk.dsg.BE.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MovieFilter {

    /***
     * Filters through a given list of movies and filters them out based on the search parameter
     * Filter reads from the start of a movies name, so searching for a movie from the middle
     * of the title, will not wield the correct answer
     * @param movie the list of movies to be searched through
     * @param search the input from the search bar.
     * @return List of filtered movies
     */

    private ObservableList<Movie> movieSearch = FXCollections.observableArrayList();

    public ObservableList<Movie> search(ObservableList<Movie> movie, String search){
        movieSearch.clear();
        for (Movie movies : movie) {
            if (movies.getMovieName().toLowerCase().startsWith(search.toLowerCase())) {
                movieSearch.add(movies);
            }
        }
        return movieSearch;
    }
}
