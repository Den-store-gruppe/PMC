package dk.dsg.BE;

public class Category {
    private final int id;
    private String catName;

    public int getID() {
        return id;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Category(int id, String catName) {
        id = id;
        this.catName = catName;
    }
    
}
