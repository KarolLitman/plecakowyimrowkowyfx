package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Algorytm mrówkowy");

        ScrollPane s1 = new ScrollPane();
        s1.setPrefSize(1012, 720);
        s1.setContent(root);

        primaryStage.setScene(new Scene(root, 1012, 720));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}