package bvira.components;

import bvira.framework.Component;
import bvira.framework.RouteMap;
import bvira.web.WebRoute;
import bvira.util.Container;

public class HomePageComponent implements Component {
    public void registerRoutes(RouteMap routeMap) {
        routeMap.registerPresenter(new WebRoute("/"), HomePagePresenter.class);
    }

    public void registerClasses(Container container) {
        container.register(HomePagePresenter.class);
    }
}
