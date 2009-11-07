package bvira.test;

import bvira.test.abstraction.For;
import bvira.test.abstraction.HtmlPage;
import bvira.test.abstraction.HTTPInteraction;
import bvira.test.abstraction.To;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public abstract class WebEnvironment implements Closeable {
    private static WebEnvironment instance;

    public static WebEnvironment getInstance() {
        return instance;
    }

    public static void setInstance(WebEnvironment instance) {
        WebEnvironment.instance = instance;
    }

    public void close() throws IOException {
        stop();
    }


    public abstract WebEnvironment navigate(To uri);

    public abstract <T extends HtmlPage> T responsePage(Class<T> pageClass);

    public abstract WebEnvironment start();
    public abstract void stop();
    public abstract WebEnvironment clickLink(String linkText);
    public abstract List<HTTPInteraction> findHttpInteractions(For clause);

    public abstract void clearHttpInteractionLog();
}
