package bvira.model;

import bvira.util.Container;
import bvira.web.RouteMap;

public interface Component {
    void registerClasses(Container container);

    void registerRoutes(RouteMap routeMap);
}
