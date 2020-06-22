package sample.manager;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Visitor extends SimpleFileVisitor<Path> {


    private Path source;
    private Path target;


    public Visitor(Path source, Path target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        Path retaltivize = source.relativize(dir);
        Path resolved = target.resolve(retaltivize);
        Files.createDirectories(resolved);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path fileRetaltivize = source.relativize(file);
        Path fileResolved = target.resolve(fileRetaltivize);
        Files.copy(file.toAbsolutePath(), fileResolved, StandardCopyOption.REPLACE_EXISTING);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }



}


