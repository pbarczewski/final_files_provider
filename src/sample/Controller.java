package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.alerts.Alerts;
import sample.file.FChooser;
import sample.file.MultipleDirectoryChooser;
import sample.file.SingleDirectoryChooser;
import sample.filter.InDesignFilter;
import sample.filter.PathFilter;
import sample.manager.DeleteDirectory;
import sample.manager.FileManager;
import sample.paths.Directories;
import sample.paths.PathBuilder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Controller {


    private final Stage thisStage;

    @FXML
    private ListView sourceCodes, finalFiles;

    @FXML
    private BorderPane mainWindow;

    @FXML
    private TextField targetField;

    private ArrayList<Path> sourceDirectories = new ArrayList<>();
    private ArrayList<Path> filestToDelivery = new ArrayList<>();
    private Path targetPath;

    public Controller() {

        thisStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            loader.setController(this);
            thisStage.setScene(new Scene(loader.load()));
            thisStage.setResizable(false);
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
      //  targetField.setText(System.getProperty("user.home"));
        targetField.setText("E:\\Target");
        targetPath = Paths.get(targetField.getText());
    }

    public void setSourcePath() {
        FChooser multipleFileChooser = new MultipleDirectoryChooser(sourceDirectories, sourceCodes);
        multipleFileChooser.run();
    }

    public void deleteItemFromList() {
        Path path =  (Path) sourceCodes.getSelectionModel().getSelectedItem();
        if(sourceDirectories != null) {
            sourceDirectories.remove(path);
            sourceCodes.getItems().remove(path);
        }
    }

    public void test() {
        if(filestToDelivery.size() > 0) {
            Path path = Paths.get(filestToDelivery.get(0).toAbsolutePath().toString());
            System.out.println(path);
            try {
                Runtime.getRuntime().exec("explorer.exe /select," + path);
            } catch (IOException e) {

            }
        } else {
            new Alerts.Builder()
                    .alertType(new Alert(Alert.AlertType.INFORMATION))
                    .title("Empty path")
                    .content("There is no files for delivery")
                    .build();
        }
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
               FileManager fileManager = new FileManager(singlePath, filestToDelivery);
               fileManager.copyFiles(pathFilter, pathBuilder);
           }
           sourceCodes.getItems().clear();
           finalFiles.getItems().setAll(filestToDelivery);
           sourceDirectories.removeAll(sourceDirectories);
        }
    }

    public void remove() {
        try {
            if(finalFiles != null) {
                Path path =  (Path) finalFiles.getSelectionModel().getSelectedItem();
                Files.walkFileTree(path, new DeleteDirectory());
                finalFiles.getItems().remove(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
