package dk.dsg.gui.controller;

import dk.dsg.BE.Category;
import dk.dsg.BLL.util.AlertSystem;
import dk.dsg.gui.model.CategoryModel;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCategoryController {

    private final CategoryModel categoryModel;
    
    public TextField categoryName;
    public Button categorySubmit;
    public Button categoryCancel;

    public NewCategoryController() {
        categoryModel = new CategoryModel();
    }


    /***
     * Gets the title from the text field, and creates a new category.
     * It will alert the user if there is nothing in the title.
     */
    public void submitCategory(ActionEvent actionEvent) {
        String title = categoryName.getText();

        if(title == null) {
            AlertSystem.alertUser("Missing Argument","Missing argument...","Remember to give the category a name");
            return;
        }

        Category category = new Category(-1, title);
        categoryModel.addCategory(category);

    }

    /***
     * Closes the window
     */
    public void cancelCategory(ActionEvent actionEvent) {
        Stage stage = (Stage) categoryCancel.getScene().getWindow();
        stage.close();
    }
}
