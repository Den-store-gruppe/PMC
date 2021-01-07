package dk.dsg.gui.controller;

import dk.dsg.BE.Movie;
import dk.dsg.BLL.MovieManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Date;
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

    public NewMovieController(){
        this.movieManager = new MovieManager();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movieCategory.getItems().setAll(movieCategories);
    }

    public void SelectMovieFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Select the movie...");
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

    public void CancelNewMovie(ActionEvent actionEvent) {
        Stage stage = (Stage) movieCancel.getScene().getWindow();
        stage.close();
    }
}
