package bvira.test;

import bvira.test.abstraction.DriverAdaptor;
import bvira.test.abstraction.For;
import bvira.test.abstraction.HTTPInteraction;
import bvira.test.abstraction.HtmlPage;
import bvira.test.abstraction.To;
import bvira.test.abstraction.WebDriverAdaptor;
import bvira.webserver.WebServer;
import intercept.configuration.ProxyConfig;
import intercept.logging.ApplicationLog;
import intercept.logging.EventLog;
import intercept.model.LogEntry;
import intercept.proxy.InterceptProxy;
import intercept.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

public class WorkflowTestWebEnvironment extends WebEnvironment implements Closeable {
    WebServer webServer;
    private WebDriver driver;
    private ProxyServer proxyServer;
    private static final int PROXY_PORT = 2001;

    public WebEnvironment navigate(To uri) {
        driver.get("http://localhost:8080" + uri.getPath());
        return this;
    }

    public <T extends HtmlPage> T responsePage(Class<T> pageClass) {
        try {
            return pageClass.getConstructor(DriverAdaptor.class).newInstance(new WebDriverAdaptor(driver));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public WebEnvironment start() {

        proxyServer = startProxy(PROXY_PORT);

        startBrowser(PROXY_PORT);

        webServer = new WebServer();
        webServer.start();
        return this;
    }

    private void startBrowser(int proxyPort) {
        FirefoxProfile profile = new FirefoxProfile();

        profile.setPreference("network.proxy.http", "localhost");
        profile.setPreference("network.proxy.http_port", proxyPort);
        profile.setPreference("network.proxy.type", 1);

        driver = new FirefoxDriver(profile);
    }

    private ProxyServer startProxy(int proxyPort) {
        ApplicationLog log = new ApplicationLog(){
            public void log(String s) {
            }

            public void trace(String s) {
            }
        };

        ProxyConfig config = new ProxyConfig();
        config.setPort(proxyPort);
        config.setName("Workflow testing proxy");
        return InterceptProxy.startProxy(config, log);
    }

    public void stop() {
        stopWebServer();
        stopDriver();
        stopProxyServer();
    }

    public WebEnvironment clickLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
        return this;
    }

    public List<HTTPInteraction> findHttpInteractions(For clause) {
        List<HTTPInteraction> interactions = new ArrayList<HTTPInteraction>();
        EventLog logger = proxyServer.getLogs();

        List<LogEntry> entries = logger.getEntries();

        for (LogEntry entry : entries) {
            if (entry.elements.size() > 3) {
                if (entry.elements.get(3).getMessage().startsWith(clause.getDomain())) {
                    interactions.add(new HTTPInteraction(){});
                }
            }
        }

        return interactions;
    }

    public void clearHttpInteractionLog() {
        proxyServer.getLogs().clear();
    }

    private void stopDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    private void stopWebServer() {
        if (webServer != null) {
            webServer.stop();
            webServer = null;
        }
    }

    private void stopProxyServer() {
        if (proxyServer != null) {
            proxyServer.stop();
            proxyServer = null;
        }
    }
}
