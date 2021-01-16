package dk.dsg.gui.model;

import dk.dsg.BE.Category;
import dk.dsg.BLL.CategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryModel {

    private ObservableList<Category> allCategories = FXCollections.observableArrayList();

    private final CategoryManager categoryManager;

    public CategoryModel() {
        categoryManager = new CategoryManager();
    }

    /***
     * Puts all the categories from the CategoryManager and inserts them into an ObservableList
     * @return a list of all the categories.
     */
    public ObservableList<Category> getAllCategories() {
        allCategories = FXCollections.observableArrayList();
        allCategories.addAll(categoryManager.getAllCategories());
        return allCategories;
    }

    /***
     * Adds a category to the ObservableList
     * @param category
     */
    public void addCategory(Category category) {
        categoryManager.addCategory(category);
    }

    /***
     * Updates the category
     * @param category
     */
    public void updateCategory(Category category) {
        categoryManager.updateCategory(category);
        allCategories.clear();
        allCategories.addAll(categoryManager.getAllCategories());
    }

    /***
     * Deletes the category
     * @param category
     */
    public void deleteCategory(Category category) {
        categoryManager.deleteCategory(category);
    }
}
