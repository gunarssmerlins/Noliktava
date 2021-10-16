package pack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class KursKlients implements Initializable {
    public static String whatOp;

    @FXML private ComboBox customerFX;
    @FXML private Button okFX;
    @FXML private Button backFX;
    @FXML private Label nosaukumsFX;

    String whichCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nosaukumsFX.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        System.out.println(whatOp);

        //Ielasam lietotājvārdus no db
        ObservableList<String> options = FXCollections.observableArrayList();

        mysqlConnection mysqlConnection = new mysqlConnection();
        Connection connection = mysqlConnection.getConnection();
        String sql = "SELECT companyName FROM noliktava.customers";
        Statement pst = null;
        ResultSet rs = null;
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        while (true) {
            try {
                if (!rs.next()) break;
                options.add(rs.getString("companyName"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        customerFX.setItems(options);

        try {
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        customerFX.setOnAction((event) -> {
            int selectedIndex = customerFX.getSelectionModel().getSelectedIndex();
            Object selectedItem = customerFX.getSelectionModel().getSelectedItem();

            whichCustomer = (String) customerFX.getValue();

            System.out.println("Izvēlēts: [" + selectedIndex + "] " + selectedItem +
                    "\n\tComboBox.getValue(): " + customerFX.getValue());

        });
    }

    public void toNextView(ActionEvent event) throws IOException {
        KlientaKrajumi.whichCustomerStock = whichCustomer;
        String sceneName = "KlientaKrajumi.fxml";
        //Select case
        switch(whatOp) {
//            case "put":
//                sceneName = "";
//                break;
//            case "pull":
//                sceneName = "";
//                break;
            case "view":
                sceneName = "KlientaKrajumi.fxml";
                break;
            default:
                // code block
        }

        Parent viewOrders = FXMLLoader.load(getClass().getResource(sceneName));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }

    public void backToControPanel(ActionEvent event) throws IOException {
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KoDarit.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }
}
