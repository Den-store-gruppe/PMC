package dk.dsg.gui.controller;

import dk.dsg.BE.Category;
import dk.dsg.BE.Movie;
import dk.dsg.BLL.util.AlertSystem;
import dk.dsg.gui.model.CategoryModel;
import dk.dsg.gui.model.MovieCatModel;
import dk.dsg.gui.model.MovieModel;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {


    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private MovieCatModel movieCatModel;

    @FXML private TableView<Movie> movieTable;
    @FXML private TableColumn<Movie, String> movieTableName;
    @FXML private TableColumn<Movie, Integer> ratingTable;
    @FXML private TableView<Category> categoryTable;
    @FXML private TableColumn<Category, String> categoryNameTable;

    @FXML private Button createMovie;
    @FXML private Button editMovie;
    @FXML private Button playMovie;
    @FXML private Button deleteMovie;
    @FXML private Button searchButton;
    @FXML private Button createCategory;
    @FXML private Button deleteCategory;

    @FXML private Label movieName;
    @FXML private Label dateLabel;
    @FXML private Label ratingLabel;
    @FXML private Label filePathLabel;
    @FXML private Label categoriesLabel;

    public TextField searchField;

    private Movie selectedMovie = null;
    private Category selectedCategory = null;

    /***
     * Sets up the tableview that contains all of the movies
     * @see TableView
     * @see TableColumn
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            movieTable.setItems(movieModel.getAllMovies());
            movieTableName.setCellValueFactory(celldata -> celldata.getValue().movieNameProperty());
            ratingTable.setCellValueFactory(new PropertyValueFactory<>("rating"));

            movieTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue)->{
                selectedMovie = newValue;
                updateInformation();
            });

            if(movieTable.getItems().size() != 0){
                movieTable.getSelectionModel().select(0);
                updateInformation();
            }

            categoryTable.setItems(categoryModel.getAllCategories());
            categoryNameTable.setCellValueFactory(celldata -> celldata.getValue().getCatNameProperty());

            categoryTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                selectedCategory = newValue;
                sortCategory();
            });

            if(categoryTable.getItems().size() != 0) {
                categoryTable.getSelectionModel().select(0);
                updateInformation();
            }

        } catch (SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }

        remindUser();

    }

    private void sortCategory() {
        try {
            ObservableList<Movie> filteredMovies = movieModel.filterMovie(movieModel.getAllMovies(), selectedCategory);
            if(filteredMovies != null){
                movieTable.setItems(filteredMovies);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void remindUser() {
        List<String> movieNames = new ArrayList<>();
        try {
            List<Movie> movies = movieModel.getAllMovies();
            for (Movie m : movies){

                if(Period.between(LocalDate.now(), m.getLastView().toLocalDate()).getMonths() <= -24 || m.getRating() < 6){
                    movieNames.add(m.getMovieName());
                }

            }

            if(movieNames.size() > 0) {
                String moviesToDelete = "";
                for(String name : movieNames){
                    moviesToDelete += name + "\r\n";
                }
                AlertSystem.alertUser("Delete movies...", "","Heres a list of movies that you haven't watched in over 2 years, or that you have rated below 6:\n" + moviesToDelete);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /***
     * Updates the information on the right side of the
     * screen, if there is a movie set.
     * @see Movie
     */
    private void updateInformation() {

        if(selectedMovie != null){
            movieName.setText(selectedMovie.getMovieName());
            dateLabel.setText(selectedMovie.getLastView().toString());
            filePathLabel.setText(selectedMovie.getFilePath());

            String categories = "";
            if(selectedMovie.getCategories().size() > 0){
                for (Category c : selectedMovie.getCategories()) {
                    categories += c.getCatName().concat(", ");
                }
            } else {
                categories = "No categories recorded";
            }

            categoriesLabel.setText(categories);

            String rating = (selectedMovie.getRating() != -1) ? String.valueOf(selectedMovie.getRating()) : "Not rated yet";

            ratingLabel.setText(rating);
        }else{
            if(movieTable.getItems().size() > 0){
                movieTable.getSelectionModel().select(0);
                updateInformation();
            }
        }
    }

    public MainViewController(){
        categoryModel = new CategoryModel();
        movieCatModel = new MovieCatModel();
        movieModel = new MovieModel();
    }

    /***
     * Creates a new instance of NewMovie and updates
     * the table to include the new movie
     * @see NewMovieController
     * @see Movie
     */
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

    public void createNewCategory(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/NewCategory.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * Launches the systems default video-player to play the selected Movie
     * @see Desktop
     * @see File
     */
    public void playMovie(ActionEvent actionEvent) {
        if(selectedMovie != null){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(new File(selectedMovie.getFilePath()));
                Movie tmp = new Movie(selectedMovie.getID(), selectedMovie.getMovieName(), selectedMovie.getRating(), selectedMovie.getFilePath(), new Date(System.currentTimeMillis()));
                movieModel.updateMovie(tmp);
            } catch (IOException | SQLException e) {
                //TODO: show error
                e.printStackTrace();
            }
        }
    }

    /***
     * Deletes a movie from the database and the list
     */
    public void deleteMovie(ActionEvent actionEvent) {
        if(selectedMovie != null){
            movieCatModel.deleteMovieCat(selectedMovie);
            movieModel.deleteMovie(selectedMovie);
            try {
                movieTable.setItems(movieModel.getAllMovies());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteCategory(ActionEvent actionEvent) {
        if(selectedCategory != null) {
            categoryModel.deleteCategory(selectedCategory);
            categoryTable.setItems(categoryModel.getAllCategories());
        }
    }

    public void searchMovie(ActionEvent actionEvent) throws SQLException {
        if (searchField.getText() == null || searchField.getText().length() <= 0) {
            movieTable.setItems(movieModel.getAllMovies());
        }
        else {
            ObservableList<Movie> movieSearcher = movieModel.searchMovie(movieModel.getAllMovies(), searchField.getText());
            if (searchField != null) {
                movieTable.setItems(movieSearcher);
            }
        }
    }

    public void editMovie(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/NewMovie.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            NewMovieController nmc = loader.getController();
            nmc.insertData(selectedMovie);

            stage.showAndWait();
            movieTable.setItems(movieModel.getAllMovies());
        } catch (IOException | SQLException e) {
            //TODO: give user the warning
            e.printStackTrace();
        }
    }
}
