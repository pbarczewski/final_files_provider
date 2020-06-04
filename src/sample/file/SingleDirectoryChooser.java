package sample.file;

import javafx.scene.control.TextField;

import javax.swing.*;
import java.nio.file.Path;

public class SingleDirectoryChooser extends FChooser {

    private Path targetPath;
    private TextField targetField;

    public SingleDirectoryChooser(Path targetPath, TextField targetField) {
        super();
        this.targetPath = targetPath;
        this.targetField = targetField;
    }

    @Override
    public void run() {
        selectSourceFile();
    }

    public void selectSourceFile() {
        if (this.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            targetPath = this.getSelectedFile().toPath();
            targetField.setText(targetPath.toAbsolutePath().toString());
        }
    }
}
