package bvira.framework;

public interface Navigable {
    /* TODO: Remove setPath it is a smell in the interface and should
     * be removed from the framework when the method is no longer
     * required. */
    void setPath(String path);
    String getPath();
}
