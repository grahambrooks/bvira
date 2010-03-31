package bvira.web;

import bvira.framework.Command;
import bvira.framework.Presenter;
import bvira.framework.RequestContext;
import bvira.framework.RequestUri;
import bvira.framework.ResponseContext;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class WebRouteMapUnitTests {

    @Test
    public void testCanCreateRouteMap() {
        new WebRouteMap();
    }

    @Test
    public void testCanRegisterRoute() {
        WebRouteMap map = new WebRouteMap();

        map.registerPresenter(new WebRoute("/"), null);
    }

    @Test
    public void testRouteMapMatchesSuppliedRoute() {
        WebRouteMap map = new WebRouteMap();

        class APresenter implements Presenter {

            public void present(RequestContext requestContext, ResponseContext responseContext) {
            }
        }

        map.registerPresenter(new WebRoute("/"), APresenter.class);

        RequestUri requestUri = new RequestUri("/", "");
        assertSame(APresenter.class, map.findPresenter(requestUri));
    }

    @Test
    public void routeMapHandlesCommandMatching() {
        WebRouteMap map = new WebRouteMap();
        class ACommand implements Command {

            public void execute(RequestContext requestContext, ResponseContext responseContext) {
            }
        }

        map.registerRoute(new WebRoute("route"), null, ACommand.class);

        assertSame(ACommand.class, map.findCommand(new RequestUri("route", "")));
    }
}
