package dk.dsg.gui.controller;

import dk.dsg.BE.Category;
import dk.dsg.BE.Movie;
import dk.dsg.BLL.MovieManager;
import dk.dsg.gui.model.CategoryModel;
import dk.dsg.gui.model.MovieModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewMovieController implements Initializable {

    private MovieModel movieModel;
    private CategoryModel catModel;

    @FXML private TextField movieName;
    @FXML private ComboBox<String> movieCategory;
    @FXML private TextField moviePath;
    @FXML private TextField ratingNumber;

    @FXML private Button movieSubmit;
    @FXML private Button movieCancel;
    @FXML private Button movieSelect;

    private List<ChoiceBox<String>> choiceboxes;

    public NewMovieController(){
        movieModel = new MovieModel();
        catModel = new CategoryModel();
        choiceboxes = new ArrayList<>();
    }

    /***
     * Sets the item on the ComboBox
     * @see ComboBox
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Category> tmpCategories = catModel.getAllCategories();
        for(Category c : tmpCategories){
            movieCategory.getItems().add(c.getCatName());
        }
    }

    /***
     * Uses the FileChooser the select the movie wanted to be listed in the database.
     * Only files with the supported types .mp4 and .mpeg4 can be selected
     * @see FileChooser
     */
    public void SelectMovieFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select the movie...");
        fc.setInitialDirectory(new File("Movies/"));//Movies folder will pop up when Select Movie is clicked
        fc.setTitle("Select movie");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Audio Files", "*.mp4", "*.mpeg4")
        );
        File selectedFile = fc.showOpenDialog(movieSelect.getScene().getWindow());

        if (selectedFile != null) {
            moviePath.setText(selectedFile.getAbsolutePath());
            String[] args = selectedFile.getAbsolutePath().split("\\\\");
            String name = args[args.length - 1].substring(0, args[args.length - 1].length() - 4);
            movieName.setText(name);
        } else {
            //TODO: Handle the missing the error
        }
    }

    /***
     * Gets all the given information about the movies, and then
     * checks if they all have a value. Should one or more be
     * empty, the user will be warned about this, and the submission
     * will be ignored.
     */
    public void SubmitMovie(ActionEvent actionEvent) {
        String title = movieName.getText();
        String path = moviePath.getText();
        String category = movieCategory.getSelectionModel().getSelectedItem();
        int rating = (!ratingNumber.getText().isEmpty()) ? Integer.parseInt(ratingNumber.getText()) : -1;

        if(category == null || title == null || path == null || rating == -1) {
            alertUser("Missing arguments","Please remember to either select a movie, or give it a category. A name is automatically found based on the name of the file.");
            return;
        }

        if(rating > 10 || rating < 0) {
            alertUser("Rating out of bounds", "Only rate movies between the values of 0 and 10. You inserted \"" + rating + "\" which is invalid. Try again");
            return;
        }

        try {
            for(Movie m : movieModel.getAllMovies()){
                if (m.getMovieName().equals(title)) {
                    alertUser("Movie already existing", "A movie with the same name has already been registered. Please make sure this is either a unique movie, and if so, make sure it has a unique name");
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Movie movie = new Movie(-1,title,rating,path,new Date(System.currentTimeMillis()));

        ObservableList<Category> cats = catModel.getAllCategories();
        for(ChoiceBox<String> box : choiceboxes){
            for (Category cat: cats) {
                if(cat.getCatName().toLowerCase().equals(box.getSelectionModel().getSelectedItem().toLowerCase())){
                    movie.addCategory(cat);
                }
            }
        }

        for (Category cat: cats) {
            if(cat.getCatName().toLowerCase().equals(movieCategory.getSelectionModel().getSelectedItem().toLowerCase())){
                movie.addCategory(cat);
            }
        }

        movieModel.addMovie(movie);

        Stage stage = (Stage) movieSelect.getScene().getWindow();
        stage.close();

    }

    private void alertUser(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText("Error occured while inserting the movie...");
        alert.setContentText(msg);
        alert.show();
    }

    /***
     * Closes the window
     * @see Stage
     */
    public void CancelNewMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) movieCancel.getScene().getWindow();
        stage.close();
    }

    /***
     * Dynamically adds more category-boxes, so that movies can have more than
     * one category assigned.
     * @see ChoiceBox
     */
    public void addCategory(ActionEvent actionEvent) {
        if(choiceboxes.size() < 4){
            ChoiceBox<String> choiceBox = new ChoiceBox<>(movieCategory.getItems());

            if (choiceboxes.size() > 0) {
                ChoiceBox<String> tmp = choiceboxes.get(choiceboxes.size() - 1);

                choiceBox.setPrefSize(tmp.getPrefWidth(), tmp.getPrefHeight());
                choiceBox.setLayoutX(tmp.getLayoutX());
                choiceBox.setLayoutY(tmp.getLayoutY() + 34);
            } else {
                choiceBox.setPrefSize(movieCategory.getPrefWidth(), movieCategory.getPrefHeight());
                choiceBox.setLayoutX(movieCategory.getLayoutX());
                choiceBox.setLayoutY(movieCategory.getLayoutY() + 34);
            }

            AnchorPane root = (AnchorPane) movieCategory.getScene().getRoot();
            root.getChildren().add(choiceBox);
            choiceboxes.add(choiceBox);
        }
    }

    /***
     * Removes the added category-boxes from the bottom up
     */
    public void removeCategory(ActionEvent actionEvent) {
        if(!choiceboxes.isEmpty()){
            AnchorPane root = (AnchorPane) movieCategory.getScene().getRoot();
            root.getChildren().remove(choiceboxes.get(choiceboxes.size() - 1));
            choiceboxes.remove(choiceboxes.size() - 1);
        }
    }
}
