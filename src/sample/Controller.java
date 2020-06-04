package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.alerts.Alerts;
import sample.file.FChooser;
import sample.file.MultipleDirectoryChooser;
import sample.file.SingleDirectoryChooser;
import sample.filter.InDesignFilter;
import sample.filter.PathFilter;
import sample.manager.FileManager;
import sample.paths.Directories;
import sample.paths.PathBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controller {


    private final Stage thisStage;

    @FXML
    private ListView sourceCodes;

    @FXML
    private BorderPane mainWindow;

    @FXML
    private TextField targetField;

    private ArrayList<Path> sourceDirectories = new ArrayList<>();
    private Path targetPath;

    public Controller() {

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setTitle("Delivery manager");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStage() {
        thisStage.show();
    }


    public void setTargetPath() {
       FChooser singleDirectoryChooser = new SingleDirectoryChooser(targetPath, targetField);
       singleDirectoryChooser.run();
    }

    public void initialize() {
        targetField.setText(System.getProperty("user.home"));
        targetPath = Paths.get(targetField.getText());
    }

    public void setSourcePath() {
        FChooser multipleFileChooser = new MultipleDirectoryChooser(sourceDirectories, sourceCodes);
        multipleFileChooser.run();
    }

    public ArrayList<Path> deleteItemFromList() {
        Path path =  (Path) sourceCodes.getSelectionModel().getSelectedItem();
        if(sourceDirectories != null) {
            sourceDirectories.remove(path);
            sourceCodes.getItems().remove(path);
        }
        return null;
    }

    public void copyFiles() {

       if(!targetPath.toString().equals(targetField.getText())) {
            targetPath = Paths.get(targetField.getText());
        }
        if(targetField.getText().isEmpty() || sourceDirectories.size() == 0) {
            new Alerts.Builder()
                    .alertType(new Alert(Alert.AlertType.WARNING))
                    .title("Empty path")
                    .content("Source path or target path is empty. Please select.")
                    .build();
        }
        else {
           PathFilter pathFilter = new InDesignFilter();
           for(Path singlePath : sourceDirectories) {
               PathBuilder pathBuilder = new PathBuilder(singlePath, targetPath, Directories.INND);
               new FileManager(singlePath).copyFiles(pathFilter, pathBuilder);
           }
        }
    }


}
