package dk.dsg.BLL.util;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertSystem {

    public static void alertUser(String title,String subMsg, String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING);

        //Make sure the alert stays on top!
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);

        alert.setTitle(title);
        alert.setHeaderText(subMsg);
        alert.setContentText(msg);
        alert.show();
    }

}
