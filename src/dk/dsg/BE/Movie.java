package dk.dsg.BE;

import java.util.Date;

public class Movie {

    private final int id;
    private String movieName;
    private final int rating;
    private String filePath;
    private Date lastView;

    public Movie(int id, String movieName, int rating, String filePath, Date lastView) {
        this.id = id;
        this.movieName = movieName;
        this.rating = rating;
        this.filePath = filePath;
        this.lastView = lastView;
    }

    public int getID() {
        return id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRating() {
        return rating;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getLastView() {
        return lastView;
    }

    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }
}
