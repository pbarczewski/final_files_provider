package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller controller = new Controller();
        controller.showStage();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    }


    public static void main(String[] args) {
        launch(args);
    }
}
