package bvira;

public interface FileSystem {
    void mkdir(String directoryName);

    void copy(String from, String to);
}
