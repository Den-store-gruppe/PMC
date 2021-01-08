package dk.dsg.DAL;

import dk.dsg.BE.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private DatabaseDAO databaseConnector;

    public CategoryDAO() {
        databaseConnector = new DatabaseDAO();
    }

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
}
