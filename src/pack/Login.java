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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML private ComboBox usernameFX;
    @FXML private PasswordField passwordFX;
    @FXML private Button okFX;
    @FXML private  Button exitFX;
    @FXML private Label wrongPassFX;

    @FXML
    public void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitFX.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Ielasam lietotājvārdus no db
        ObservableList<String> options = FXCollections.observableArrayList();

        mysqlConnection mysqlConnection = new mysqlConnection();
        Connection connection = mysqlConnection.getConnection();
        String sql = "SELECT user FROM noliktava.users";
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
                options.add(rs.getString("user"));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        usernameFX.setItems(options);

        try {
            pst.close();
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
//
//        usernameFX.setItems(conn.listColumn("SELECT user FROM noliktava.users"));

//        okFX.setOnAction();

    }

    public void checkPassw(ActionEvent event) throws SQLException, IOException {
        mysqlConnection mysqlConnection = new mysqlConnection();
        Connection connection = mysqlConnection.getConnection();
        String sql = "SELECT password FROM noliktava.users WHERE user='" + usernameFX.getValue() + "'";
        Statement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery(sql);

        while (rs.next()) {
            if (passwordFX.getText().equals(rs.getString("password"))) {
                Parent viewOrders = FXMLLoader.load(getClass().getResource("KoDarit.fxml"));
                Scene viewOrdersScene = new Scene(viewOrders);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(viewOrdersScene);
                window.show();
            } else {
                wrongPassFX.setText("Nepareiza parole");
            }
        }
    }

    }

