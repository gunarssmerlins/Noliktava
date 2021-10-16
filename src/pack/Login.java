package pack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
        usernameFX.getEditor().setFont(Font.font("Arial", FontWeight.NORMAL, 13));

        //Ielasam lietotājvārdus no db
        dbConnect db = new dbConnect();
        try {
            usernameFX.setItems( db.getObservableListFromColumn("SELECT user FROM noliktava.users", "user"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        usernameFX.setOnAction((event) -> {
            int selectedIndex = usernameFX.getSelectionModel().getSelectedIndex();
            Object selectedItem = usernameFX.getSelectionModel().getSelectedItem();
            System.out.println(event);

            System.out.println("Izvēlēts: [" + selectedIndex + "] " + selectedItem +
                    "\n\tComboBox.getValue(): " + usernameFX.getValue());
        });

        usernameFX.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {

            }
        });
    }

    public void onComboBoxClick(){

    }


    // uz ko darīt logu.
    public void checkPassw (ActionEvent event) throws SQLException, IOException {
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

