package dk.dsg.gui.controller;

import dk.dsg.BE.Movie;
import dk.dsg.gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    private MovieModel movieModel;

    private final String[] movieCategories = {"Action","Adventure","Comedy","Crime","Drama","Historical","Horror","Musical","Science Fiction","War",    "Western"};

    @FXML private TableView<Movie> movieTable;
    @FXML private TableColumn<Movie, String> movieTableName;

    @FXML private Button createMovie;
    @FXML private Button editMovie;
    @FXML private Button playMovie;
    @FXML private Button deleteMovie;

    @FXML private Label movieName;
    @FXML private Label dateLabel;
    @FXML private Label ratingLabel;
    @FXML private Label filePathLabel;

    private Movie selectedMovie = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            movieTable.setItems(movieModel.getAllMovies());
            movieTableName.setCellValueFactory(celldata -> celldata.getValue().movieNameProperty());

            movieTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->{
                selectedMovie = newValue;
                updateInformation();
            });

        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

    private void updateInformation() {

        if(selectedMovie != null){
            movieName.setText(selectedMovie.getMovieName());
            dateLabel.setText(selectedMovie.getLastView().toString());
            filePathLabel.setText(selectedMovie.getFilePath());

            String rating = (selectedMovie.getRating() != -1) ? String.valueOf(selectedMovie.getRating()) : "Not rated yet";

            ratingLabel.setText(rating);
        }else{
            //TODO: handle no movie selected
        }
    }

    public MainViewController(){
        movieModel = new MovieModel();
    }

    public void createNewMovie(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/NewMovie.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            movieTable.setItems(movieModel.getAllMovies());
        } catch (IOException | SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }

    public void playMovie(ActionEvent actionEvent) {
        if(selectedMovie != null){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(new File(selectedMovie.getFilePath()));
            } catch (IOException e) {
                //TODO: show error
                e.printStackTrace();
            }
        }
    }

    public void deleteMovie(ActionEvent actionEvent) {
        if(selectedMovie != null){
            movieModel.deleteMovie(selectedMovie);
            try {
                movieTable.setItems(movieModel.getAllMovies());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
