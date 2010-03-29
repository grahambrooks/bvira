package bvira.framework;

import bvira.framework.Navigable;

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

    public RequestUri without(String context) {
        int beginIndex = context.length();
        return new RequestUri(path.substring(beginIndex), query);
    }

    public String getExtension() {
        int extensionPos = path.lastIndexOf('.');
        return (extensionPos < 0) ? "" : path.substring(extensionPos + 1);
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    public String toString() {
        if (query != null) {
            return path + "?" + query;
        }
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
