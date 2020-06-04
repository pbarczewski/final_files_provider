package sample.filter;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public abstract class PathFilter implements DirectoryStream.Filter<Path> {

    @Override
    public boolean accept(Path entry) throws IOException {
        return false;
    }

}
