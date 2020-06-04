package sample.file;

import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

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
                sourceDirectories.add(file.toPath());
            }
            sourceCodes.getItems().setAll(sourceDirectories);
        }

    }
}
