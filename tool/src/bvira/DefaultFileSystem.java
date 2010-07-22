package bvira;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DefaultFileSystem implements FileSystem {

    public void mkdir(String directoryName) {
        new File(directoryName).mkdir();
    }

    public void copy(String from, String to) {
        copyFiles(new File(from), new File(to));
    }

    public static void copyFiles(File src, File dest) {
        //Check to ensure that the source is valid...
        if (!src.exists()) {
            throw new RuntimeException("copyFiles: Can not find source: " + src.getAbsolutePath() + ".");
        } else if (!src.canRead()) {
            throw new RuntimeException("copyFiles: No right to source: " + src.getAbsolutePath() + ".");
        }

        if (src.isDirectory()) {
            copyDirectory(src, dest);
        } else {
            copyFile(src, dest);
        }
    }

    private static void copyDirectory(File src, File dest) {
        if (!dest.exists()) {

            if (!dest.mkdirs()) {
                throw new RuntimeException("copyFiles: Could not create direcotry: " + dest.getAbsolutePath() + ".");
            }
        }

        //get a listing of files...
        String list[] = src.list();

        //copy all the files in the list.
        for (String filename : list) {
            copyFiles(new File(src, filename), new File(dest, filename));
        }
    }

    private static void copyFile(File source, File destination) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(destination);

            copyContents(inputStream, outputStream);

        } catch (IOException e) { //Error copying file...
            RuntimeException wrapper = new RuntimeException("copyFiles: Unable to copy file: " + source.getAbsolutePath() + "to" + destination.getAbsolutePath() + ".");
            wrapper.initCause(e);
            wrapper.setStackTrace(e.getStackTrace());
            throw wrapper;
        } finally {
            close(inputStream);
            close(outputStream);
        }
    }

    private static void copyContents(FileInputStream inputStream, FileOutputStream outputStream) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer, 0, bytesRead);
        }
    }

    private static void close(Closeable f) {
        try {
            if (f != null) {
                f.close();
            }
        } catch (IOException e) {
            // Ignored
        }
    }
}
