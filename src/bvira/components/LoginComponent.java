package bvira.components;

import bvira.model.Component;
import bvira.web.RouteMap;
import bvira.web.WebRoute;
import bvira.util.Container;

public class LoginComponent implements Component {
    public void registerClasses(Container container) {
        container.register(LoginPresenter.class);
        container.register(LoginCommand.class);
    }

    public void registerRoutes(RouteMap routeMap) {
        routeMap.registerPresenter(new WebRoute("/login"), LoginPresenter.class, LoginCommand.class);
    }
}
