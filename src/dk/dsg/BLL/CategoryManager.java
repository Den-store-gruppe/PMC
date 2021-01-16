package dk.dsg.BLL;

import dk.dsg.BE.Category;
import dk.dsg.DAL.CategoryDAO;

import java.util.List;

public class CategoryManager {
    private final CategoryDAO categoryDAO;

    public CategoryManager() {
        categoryDAO = new CategoryDAO();
    }

    /***
     * Puts all the categories from CategoryDAO and inserts them into an List
     * @return a list of all categories
     */
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    /***
     * Adds a category to the database
     * @param category
     */
    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    /***
     * Updates the category in the database
     * @param category
     */
    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    /***
     * Deletes a category from the database
     * @param category
     */
    public void deleteCategory(Category category) {
        categoryDAO.deleteMovie(category);
    }
}
