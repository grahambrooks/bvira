package bvira.webserver;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.webapp.WebAppContext;

class JettyServer {

    private final String hostName;
    private final int portNumber;
    private final String contextPath;
    private final String contextDir;

    private final Server server = new Server();

    public JettyServer(String hostName, int portNumber, String contextPath, String contextDir) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.contextPath = contextPath;
        this.contextDir = contextDir;
    }

    public void start() throws Exception {
        server.addConnector(createConnector());
        server.addHandler(createContext());
        server.setStopAtShutdown(true);
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public boolean isRunning() {
        return server.isRunning();
    }

    private Connector createConnector() {
        Connector connector = new SelectChannelConnector();
        connector.setPort(portNumber);
        connector.setHost(hostName);
        return connector;
    }

    private Context createContext() {
        WebAppContext wac = new WebAppContext();
        wac.setContextPath(contextPath);
        // this is path to .war OR TO expanded existing webapp
        // WILL FIND web.xml and parse it
        wac.setWar(contextDir);
        return wac;
    }

}
