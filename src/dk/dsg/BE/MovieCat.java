package dk.dsg.BE;

public class MovieCat {
    private int id;
    private int categoryId;
    private int movieId;

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

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
