package pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class KoDarit {
    @FXML private Button putInStockFX;
    @FXML private Button pullOutOfStockFX ;
    @FXML private Button viewStockFX;
    @FXML private Button newCustomerFX;
    @FXML private Button exitFX;

    public void chooseCustomerIn(ActionEvent event) throws IOException {
        KursKlients.whatOp = "put";
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KursKlients.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }

    public void chooseCustomerOut(ActionEvent event) throws IOException {
        KursKlients.whatOp = "pull";
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KursKlients.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }

    public void viewStock(ActionEvent event) throws IOException {
        KursKlients.whatOp = "view";
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KursKlients.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }



    public void newCustomer(ActionEvent event) throws IOException {
        Parent viewOrders = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }




    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitFX.getScene().getWindow();
        stage.close();
    }



}
