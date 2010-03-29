package bvira.webserver;

public class WebServer {
    private final JettyServer jetty;

    public static void main(String... args) throws Exception {
        new WebServer().start();
    }

    public WebServer() {
        String host = System.getProperty("jetty.host", "0.0.0.0");
        int port = Integer.parseInt(System.getProperty("jetty.port", "8080"));

        this.jetty = new JettyServer(host, port, "/", "web");
    }

    public void start() {
        try {
            jetty.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        try {
            jetty.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
