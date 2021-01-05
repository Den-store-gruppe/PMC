package dk.dsg.BE;

public class Category {
    private final int ID;
    private String catName;

    public int getID() {
        return ID;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Category(int id, String catName) {
        ID = id;
        this.catName = catName;
    }
    
}
