package sample.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IdmlFilter extends PathFilter {

    @Override
    public boolean accept(Path entry) throws IOException {
        if(Files.isRegularFile(entry) && entry.getFileName().toString().endsWith("idml")) {
            return true;
        }
        return false;
    }
}
