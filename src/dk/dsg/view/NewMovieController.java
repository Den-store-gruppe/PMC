package dk.dsg.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewMovieController implements Initializable {

    private final String[] movieCategories = {"Action","Adventure","Comedy","Crime","Drama","Historical","Horror","Musical","Science Fiction","War","Western"};

    @FXML private TextField movieName;
    @FXML private ComboBox<String> movieCategory;
    @FXML private TextField moviePath;

    @FXML private Button movieSubmit;
    @FXML private Button movieCancel;
    @FXML private Button movieSelect;

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
        }
    }
}
