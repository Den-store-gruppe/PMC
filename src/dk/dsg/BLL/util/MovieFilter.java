package dk.dsg.BLL.util;

import dk.dsg.BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieFilter {

    public ArrayList<Movie> search(List<Movie> movie, String search){
        ArrayList<Movie> result = new ArrayList<>();
        for (Movie movies : movie) {

            if (movies.getMovieName().toLowerCase().startsWith(search.toLowerCase())) {
                result.add(movies);
            }
        }
        return result;
    }
}
