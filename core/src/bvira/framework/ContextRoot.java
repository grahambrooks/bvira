package bvira.framework;

public class ContextRoot {

    private final String serverName;
    private final int port;

    public ContextRoot(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    public String getValue() {
        return String.format("http://%s:%s", serverName, port);
    }
}
