package dk.dsg.DAL;

import dk.dsg.BE.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private DatabaseDAO databaseConnector;

    public CategoryDAO() {
        databaseConnector = new DatabaseDAO();
    }

    /***
     * Gets all the categories recorded in the database and returns them as a list
     * @return A list of all categories in the database
     * @see Connection
     * @see Category
     * @see List
     */
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM Category";
        try (Connection connection = databaseConnector.getConnection()) {
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             while (resultSet.next()) {
                 categories.add(new Category(
                         resultSet.getInt("id"),
                         resultSet.getString("catName")
                 ));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    /***
     * Adds a new category to the database records
     * @param category the category we want to add to the database
     * @see Category
     */
    public void addCategory(Category category) {
        String query = "INSERT INTO Category(catName) VALUES (?)";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category.getCatName());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * Updates the database record with the same ID with the new values in the altered category object
     * @param category an altered category
     * @see Category
     */
    public void updateCategory(Category category) {
        String query = "UPDATE Category SET catName = ? WHERE id = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category.getCatName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * Deleted a given category from the database records.
     * @param category the category to be deleted
     * @see Category
     * @see Connection
     */
    public void deleteMovie(Category category) {
        String query = "DELETE from Category WHERE id = ?";
        try (Connection connection = databaseConnector.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category.getID());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
