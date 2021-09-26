package pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KursKlients implements Initializable {
    public static String whatOp;

    @FXML private ComboBox customerFX;
    @FXML private Button okFX;
    @FXML private Button backFX;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(whatOp);
    }

    public void backToControPanel(ActionEvent event) throws IOException {
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KoDarit.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }
}
