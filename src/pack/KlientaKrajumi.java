package pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KlientaKrajumi implements Initializable {
    @FXML private Label klientsFX;
    public static String whichCustomerStock;


    public void back(ActionEvent event) throws IOException {
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KoDarit.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(whichCustomerStock);
        klientsFX.setText(whichCustomerStock);

    }
}
