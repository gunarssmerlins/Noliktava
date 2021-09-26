package pack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class NewCustomer {

    @FXML private TextField customerFX;
    @FXML private Button okFX;
    @FXML private Label errorFX;


    // pārbauda pasūtījuma numura unikalitāti, nav lieks!!!
    public Boolean checkCustomer() throws SQLException {
        okFX.setDisable(false);
        customerFX.setStyle("-fx-text-inner-color: black");
        errorFX.setText("");

        mysqlConnection mysqlConnection = new mysqlConnection();
        Connection connection = mysqlConnection.getConnection();

            String sql = "SELECT companyName FROM noliktava.customers WHERE companyName = '" + customerFX.getText() + "'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("companyName").toLowerCase(Locale.ROOT).
                        equals(customerFX.getText().toLowerCase(Locale.ROOT))){
                    customerFX.setStyle("-fx-text-inner-color: red");
                    errorFX.setText("Tāds klients jau ir!");
                    errorFX.setStyle("-fx-text-inner-color: red");
                    okFX.setDisable(true);
                    return true;
                } else okFX.setDisable(false);
            }
            rs.close();
            statement.close();
        return false;
    }

    // pievieno jaunu klientu datu bāzei
    public void addCustomer(ActionEvent event) throws SQLException, IOException {
        mysqlConnection mysqlConnection = new mysqlConnection();
        Connection connection = mysqlConnection.getConnection();
        String sql = "INSERT INTO customers (companyName) VALUES ('" + customerFX.getText() + "')";
        Statement statement = connection.createStatement();
        if(!checkCustomerFX()){
            statement.executeUpdate(sql);
        }
        statement.close();

        Parent viewOrders = FXMLLoader.load(getClass().getResource("KoDarit.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
    }

    //pārbauda vai db jau nesēž tāds klients
    public Boolean checkCustomerFX() throws SQLException {
        mysqlConnection mysqlConnection = new mysqlConnection();
        Connection connection = mysqlConnection.getConnection();
        String sql = "SELECT companyName FROM noliktava.customers WHERE companyName = '" + customerFX.getText() + "'";
//        System.out.println(sql);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);

        if (rs.next()) {
            System.out.println("Atrasts: " + rs.getString("companyName. Domā citu klientu."));
            statement.close();
            rs.close();
            return true;
        } else {
            System.out.println("Ierakstīts");
            statement.close();
            rs.close();
            return false;
        }
    }

    public void back(ActionEvent event) throws IOException {
        Parent viewOrders = FXMLLoader.load(getClass().getResource("KoDarit.fxml"));
        Scene viewOrdersScene = new Scene(viewOrders);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(viewOrdersScene);
        window.show();
        System.out.println("back() in action");
    }
}
