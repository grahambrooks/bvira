package bvira.test;

import bvira.test.abstraction.HtmlPage;
import bvira.test.abstraction.To;

public class AcceptanceTest {
    public static <T extends HtmlPage> T page(Class<T> pageClass, String uri) {
        return navigateTo(uri).responsePage(pageClass);
    }

    private static WebEnvironment navigateTo(String uri) {
        return WebEnvironment.getInstance().navigate(To.path(uri));
    }
}
