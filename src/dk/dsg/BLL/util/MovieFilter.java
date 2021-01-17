package dk.dsg.BLL.util;

import dk.dsg.BE.Category;
import dk.dsg.BE.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MovieFilter {

    /***
     * Filters through a given list of movies and filters them out based on the search parameter
     * @param movie the list of movies to be searched through
     * @param search the input from the search bar.
     * @return List of filtered movies
     */

    private ObservableList<Movie> movieSearch = FXCollections.observableArrayList();

    public ObservableList<Movie> search(ObservableList<Movie> movies, String search){
        movieSearch.clear();
        for (Movie movie : movies) {
            if (movie.getMovieName().toLowerCase().contains(search.toLowerCase())) {
                movieSearch.add(movie);
            }
        }
        return movieSearch;
    }

    /***
     * Filters through the movies by sorting with the categories
     * @param movies the movies to search through
     * @param category Sorting by category
     * @return List of movies belonging to the category
     */
    public ObservableList<Movie> filterByCategory(ObservableList<Movie> movies, Category category){
        movieSearch.clear();
        for (Movie movie : movies){
            for(Category c : movie.getCategories()){
                if(c.getCatName().toLowerCase().equals(category.getCatName().toLowerCase()))
                    movieSearch.add(movie);
            }
        }
        return movieSearch;
    }
}
