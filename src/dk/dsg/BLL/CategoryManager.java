package dk.dsg.BLL;

import dk.dsg.BE.Category;
import dk.dsg.DAL.CategoryDAO;

import java.util.List;

public class CategoryManager {
    private final CategoryDAO categoryDAO;

    public CategoryManager() {
        categoryDAO = new CategoryDAO();
    }

    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public void addCategory(Category category) {
        categoryDAO.addCategory(category);
    }

    public void updateCategory(Category category) {
        categoryDAO.updateCategory(category);
    }

    public void deleteCategory(Category category) {
        categoryDAO.deleteMovie(category);
    }
}
