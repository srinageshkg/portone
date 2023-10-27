package com.dcp.portone.corejava.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

public class MainFile {
    public static void main(String[] args) {
//        useFile("MicroservicesSpringBoot.txt");
//        usePath("files/pathfile.txt");
//        Path path = Path.of("files/testing.txt");
//        printPathInfo(path);
//        logStatement(path);
//        extraInfo(path);
        Path path = Path.of("");
        System.out.println("cwd = " + path.toAbsolutePath());
        try (Stream<Path> paths = Files.list(path)) {
            paths
                    .filter(Files::isRegularFile)
                    .map(MainFile::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------------------------------------");
        try (Stream<Path> paths = Files.walk(path, 3)) {
            paths
                    .filter(Files::isRegularFile)
                    .map(MainFile::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------------------------------------");
        try (Stream<Path> paths = Files.find(path, 3,
//                (p, attr) -> Files.isRegularFile(p))) {
                (p, attr) -> attr.isRegularFile() && attr.size() > 300)) {
            paths
                    .map(MainFile::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        path = path.resolve(".idea");
        System.out.println("=========Directory Stream+++++++++++++++++");
        //get path mathcer
//        try (var dirs = Files.newDirectoryStream(path)) {
        try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(d -> System.out.println(MainFile.listDir(d)));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        System.out.println("=========Directory Stream=================");
        //get path mathcer
//        try (var dirs = Files.newDirectoryStream(path)) {
        try (var dirs = Files.newDirectoryStream(path,
                p -> p.getFileName().toString().endsWith(".xml")
                && Files.isRegularFile(p) && Files.size(p) > 1000)) {
            dirs.forEach(d -> System.out.println(MainFile.listDir(d)));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private static String listDir(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateModified = Files.getLastModifiedTime(path);
            LocalDateTime modDT = LocalDateTime.ofInstant(
                    dateModified.toInstant(), ZoneId.systemDefault());
            return "%tD %tT %-5s %10s %s".formatted(modDT, modDT, (isDir ? "<DIR>" : ""),
                    (isDir ? "" : Files.size(path)), path);
        }catch (IOException e) {
            System.out.println("Exception: " + path);
            return path.toString();
        }
    }
    public static void extraInfo(Path path) {
        try {
            var atts = Files.readAttributes(path, "*");
            atts.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            System.out.println("Problem getting file attributes.");
        }
    }
    public static void logStatement(Path path) {
        try {
            Path parentPath = path.getParent();
            if (!Files.exists(parentPath)) {
                Files.createDirectories(parentPath);
            }
            Files.writeString(path, Instant.now() + ": hello from Java Practice! What a mess\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void useFile(String fileName) {
        File file = new File(fileName);
        boolean fileExists = file.exists();

        if (fileExists) {
            fileExists = !file.delete();
        }

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                //throw new RuntimeException(e);
                System.out.println("e something went wrong!= " + e);
            }
            if (file.canWrite()) {
                System.out.println("Can write to the file " + file.getAbsoluteFile());
                ; // do something here
            }
        }
    }
    public static void printPathInfo(Path path) {
        Path absolutePath = path.toAbsolutePath();

//        int i = 1;
//        var it = path.toAbsolutePath().iterator();
//
//        while (it.hasNext()) {
//            System.out.println(".".repeat(i++) + "  " + it.next());
//        }

        int pathParts = absolutePath.getNameCount();
        for (int i=0; i<pathParts; i++) {
            System.out.println(".".repeat(i+1) + " " + absolutePath.getName(i));
        }
        System.out.println("----------------------------------");
    }
    public static void usePath(String fileName) {
        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);

        if (fileExists) {
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                //throw new RuntimeException(e);
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
                if (Files.isWritable(path)) {
                    //System.out.println("Can write to the file ");// + File..getAbsoluteFile());; // do something here
                    Files.writeString(path, """
                            Here is some data,
                            for my file,
                            just to prove
                            Using the Files class and path are better!!!
                            """);
                }
                //Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                //throw new RuntimeException(e);
                System.out.println("e something went wrong!= " + e);
            }
        }
    }
}
