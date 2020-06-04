package sample.paths;

import java.nio.file.FileSystems;
import java.nio.file.Path;

public class PathBuilder {


    private Path sourcePath;
    private Path targetPath;
    private Directories directories;

    public PathBuilder(Path sourcePath, Path targetPath, Directories directories) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
        this.directories = directories;
    }

    private Path extractLanguageCode() {
        int sourceNameCount = this.sourcePath.getNameCount();
        Path languageCode = this.sourcePath.subpath(sourceNameCount-1, sourceNameCount);
        return languageCode;
    }

    public StringBuilder buildPath() {
        StringBuilder createdTargetPath = new StringBuilder(this.targetPath.toString())
                .append(FileSystems.getDefault().getSeparator())
                .append(extractLanguageCode())
                .append(FileSystems.getDefault().getSeparator())
                .append(directories.getName());
        return createdTargetPath;
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
