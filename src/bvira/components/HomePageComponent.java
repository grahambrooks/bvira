package bvira.components;

import bvira.model.Component;
import bvira.web.RouteMap;
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
