package dk.dsg.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
    private final int id;
    private StringProperty catName;

    public int getID() {
        return id;
    }

    public StringProperty getCatNameProperty() {
        return catName;
    }

    public String getCatName() {
        return catName.get();
    }

    public void setCatName(String catName) {
        this.catName.set(catName);
    }

    public Category(int id, String catName) {
        this.id = id;
        this.catName = new SimpleStringProperty(catName);
    }
    
}
