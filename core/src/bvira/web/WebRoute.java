package bvira.web;

import bvira.framework.Route;

public class WebRoute implements Route {

    private final String route;

    public WebRoute(String route) {
        this.route = route;
    }

    public boolean match(String path) {
        return route.equals(path);
    }

    public boolean match(Route route) {
        return route.match(this.route);
    }
}
