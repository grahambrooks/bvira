package bvira.components;

import bvira.framework.Component;
import bvira.util.Container;
import bvira.framework.RouteMap;
import bvira.web.WebRoute;

public class OfficeComponent implements Component {
    public void registerClasses(Container container) {
        container.register(OfficeDetailsPresenter.class);
    }

    public void registerRoutes(RouteMap routeMap) {
        routeMap.registerPresenter(new WebRoute("/office/details"), OfficeDetailsPresenter.class);
    }
}
