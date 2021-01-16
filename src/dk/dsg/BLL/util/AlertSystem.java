package dk.dsg.BLL.util;

import javafx.scene.control.Alert;

public class AlertSystem {

    public static void alertUser(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText("Error occured while inserting the movie...");
        alert.setContentText(msg);
        alert.show();
    }

}
