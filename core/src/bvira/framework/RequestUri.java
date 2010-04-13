package bvira.framework;

public class RequestUri implements Navigable {

    private String path;
    private final String query;

    public RequestUri() {
        this.path = "";
        this.query = "";
    }

    public RequestUri(String path, String query) {
        this.path = path;
        this.query = query;
    }

    public final RequestUri without(String context) {
        int beginIndex = context.length();
        return new RequestUri(path.substring(beginIndex), query);
    }

    public String getExtension() {
        int extensionPos = path.lastIndexOf('.');
        return (extensionPos < 0) ? "" : path.substring(extensionPos + 1);
    }

    public final String getPath() {
        return path;
    }

    public final String getQuery() {
        return query;
    }

    public final String toString() {
        if (query != null) {
            return path + "?" + query;
        }
        return path;
    }

    public final void setPath(String path) {
        this.path = path;
    }
}
