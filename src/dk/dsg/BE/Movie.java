package dk.dsg.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Movie {

    private int id;
    private StringProperty movieName;
    private final int rating;
    private String filePath;
    private Date lastView;

    private List<Category> categories;

    public Movie(int id, String movieName, int rating, String filePath, Date lastView) {
        this.id = id;
        this.movieName = new SimpleStringProperty(movieName);
        this.rating = rating;
        this.filePath = filePath;
        this.lastView = lastView;
        categories = new ArrayList<>();
    }

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void addCategories(List<Category> categories){
        this.categories.addAll(categories);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
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

    public java.sql.Date getLastView() {
        return lastView;
    }

    public void setLastView(Date lastView) {
        this.lastView = lastView;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Category> getCategories() {
        return this.categories;
    }
}
