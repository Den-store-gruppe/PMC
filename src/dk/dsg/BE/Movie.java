package dk.dsg.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Movie {

    private final int id;
    private StringProperty movieName;
    private final int rating;
    private String filePath;
    private Date lastView;

    public Movie(int id, String movieName, int rating, String filePath, Date lastView) {
        this.id = id;
        this.movieName = new SimpleStringProperty(movieName);
        this.rating = rating;
        this.filePath = filePath;
        this.lastView = lastView;
    }

    public int getID() {
        return id;
    }

    public String getMovieName() {
        return movieName.get();
    }

    public StringProperty movieNameProperty() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
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
