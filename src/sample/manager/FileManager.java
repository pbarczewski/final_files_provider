package sample.manager;

import javafx.scene.control.ListView;
import sample.filter.PathFilter;
import sample.paths.PathBuilder;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileManager {

    private Path sourcePath;
    private Path targetPath;
    private ArrayList<Path> filesToDelivery;


    public FileManager(Path sourcePath, ArrayList<Path> filesToDelivery) {
        this.sourcePath = sourcePath;
        this.filesToDelivery = filesToDelivery;
    }

    private void createDirectory(PathBuilder buildPath) {
        if(buildPath != null) {
            try {
                this.targetPath = Files.createDirectories(Paths.get(buildPath.buildPath().toString()));
                filesToDelivery.add(targetPath.getParent());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void copyFiles(PathFilter PathFilter, PathBuilder buildPath){
        createDirectory(buildPath);
        try{
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourcePath, PathFilter);
            for (Path path : directoryStream) {
                Files.walkFileTree(path, new Visitor(sourcePath, this.targetPath));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Path getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(Path sourcePath) {
        this.sourcePath = sourcePath;
    }

    public Path getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(Path targetPath) {
        this.targetPath = targetPath;
    }
}
