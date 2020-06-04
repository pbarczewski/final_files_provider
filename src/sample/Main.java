package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller controller = new Controller();
        controller.showStage();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);

        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
