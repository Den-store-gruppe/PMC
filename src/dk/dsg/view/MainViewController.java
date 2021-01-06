package dk.dsg.view;

import dk.dsg.BE.Movie;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void CreateNewMovie(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("NewMovie.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
