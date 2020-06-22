package sample.file;

import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import sample.alerts.Alerts;

import javax.swing.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public class MultipleDirectoryChooser extends FChooser  {

    private ArrayList<Path> sourceDirectories;
    private ListView sourceCodes;

    public MultipleDirectoryChooser(ArrayList<Path> sourceDirectories, ListView sourceCodes) {
        super();
        this.setMultiSelecton(true);
        this.sourceDirectories = sourceDirectories;
        this.sourceCodes = sourceCodes;

    }


    @Override
    public void run() {
        selectSourceFiles();
    }

    public void selectSourceFiles() {
        if (this.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            for (File file : this.getSelectedFiles()) {
                if(!sourceDirectories.toString().contains(file.getAbsolutePath())) {
                    sourceDirectories.add(file.toPath());
                } else {
                    new Alerts.Builder()
                            .alertType(new Alert(Alert.AlertType.INFORMATION))
                            .content(file.getName() + " is already selected for delivery")
                            .title("Language is duplicated")
                            .build();
                }
            }
            sourceCodes.getItems().setAll(sourceDirectories);
        }

    }
}
