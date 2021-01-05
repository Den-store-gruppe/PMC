package dk.dsg.BE;

public class MovieCat {
    private final int id;
    private final int categoryId;
    private final int movieId;

    public MovieCat(int id, int categoryId, int movieId) {
        this.id = id;
        this.categoryId = categoryId;
        this.movieId = movieId;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getMovieId() {
        return movieId;
    }
}
