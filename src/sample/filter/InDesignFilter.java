package sample.filter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InDesignFilter extends PathFilter {

    @Override
    public boolean accept(Path entry) throws IOException {
        if (((Files.isDirectory(entry) && entry.toString().contains("Links") || entry.toString().contains("Document Fonts"))
                || (Files.isRegularFile(entry) && entry.getFileName().toString().endsWith("indd")))) {
            return true;
        }
        return false;
    }

}
