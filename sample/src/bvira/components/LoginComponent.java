package bvira.components;

import bvira.framework.Component;
import bvira.framework.RouteMap;
import bvira.util.Container;
import bvira.web.WebRoute;

public class LoginComponent implements Component {
    public void registerClasses(Container container) {
        container.register(LoginPresenter.class);
        container.register(LoginCommand.class);
    }

    public void registerRoutes(RouteMap routeMap) {
        routeMap.registerRoute(new WebRoute("/login"), LoginPresenter.class, LoginCommand.class);
    }
}
