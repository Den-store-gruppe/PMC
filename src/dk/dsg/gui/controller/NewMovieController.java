package dk.dsg.gui.controller;

import dk.dsg.BE.Movie;
import dk.dsg.BLL.MovieManager;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewMovieController implements Initializable {

    private MovieManager movieManager;

    private final String[] movieCategories = {"Action","Adventure","Comedy","Crime","Drama","Historical","Horror","Musical","Science Fiction","War","Western"};

    @FXML private TextField movieName;
    @FXML private ComboBox<String> movieCategory;
    @FXML private TextField moviePath;

    @FXML private Button movieSubmit;
    @FXML private Button movieCancel;
    @FXML private Button movieSelect;

    private List<ChoiceBox<String>> choiceboxes;

    public NewMovieController(){
        this.movieManager = new MovieManager();
        choiceboxes = new ArrayList<>();
    }

    /***
     * Sets the item on the ComboBox
     * @see ComboBox
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieCategory.getItems().setAll(movieCategories);
    }

    /***
     * Uses the FileChooser the select the movie wanted to be listed in the database.
     * Only files with the supported types .mp4 and .mpeg4 can be selected
     * @see FileChooser
     */
    public void SelectMovieFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select the movie...");
        fc.setInitialDirectory(new File("Movies/"));
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

        if(category == null || title == null || path == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing arguments");
            alert.setHeaderText("You are missing some arguments");
            alert.setContentText("Please remember to either select a movie, or give it a category. A name is automatically found based on the name of the file.");
            alert.show();
            return;
        }

        Movie movie = new Movie(-1,title,-1,path,new Date(System.currentTimeMillis()));
        movieManager.addMovie(movie);

        Stage stage = (Stage) movieSelect.getScene().getWindow();
        stage.close();

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
