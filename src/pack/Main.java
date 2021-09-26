package pack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Noliktava");
        Scene skats = new Scene(root, 600, 400, Color.ROYALBLUE);
        primaryStage.setScene(skats);
//        skats.getStylesheets().add("pack/stylesheet.css");
        primaryStage.setResizable(true);
        primaryStage.show();

        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }
}