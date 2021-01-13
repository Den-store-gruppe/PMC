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

    public ObservableList<Category> getAllCategories() {
        allCategories = FXCollections.observableArrayList();
        allCategories.addAll(categoryManager.getAllCategories());
        return allCategories;
    }

    public void addCategory(Category category) {
        categoryManager.addCategory(category);
    }

    public void updateCategory(Category category) {
        categoryManager.updateCategory(category);
        allCategories.clear();
        allCategories.addAll(categoryManager.getAllCategories());
    }

    public void deleteCategory(Category category) {
        categoryManager.deleteCategory(category);
    }
}
