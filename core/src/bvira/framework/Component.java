package bvira.framework;

import bvira.framework.RouteMap;
import bvira.util.Container;

public interface Component {
    void registerClasses(Container container);

    void registerRoutes(RouteMap routeMap);
}
