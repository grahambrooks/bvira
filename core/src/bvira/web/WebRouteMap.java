package bvira.web;

import bvira.framework.Command;
import bvira.framework.Navigable;
import bvira.framework.Presenter;
import bvira.framework.Route;
import bvira.framework.RouteMap;
import bvira.util.Lists;
import bvira.util.Maps;
import bvira.util.NotFoundException;

import java.util.List;
import java.util.Map;

public class WebRouteMap implements RouteMap {
    private final List<Route> routes = Lists.create();
    private final Map<Route, Class<? extends Presenter>> presenterRoutes = Maps.create();
    private final Map<Route, Class<? extends Command>> commandRoutes = Maps.create();

    public void registerPresenter(Route route, Class<? extends Presenter> presenterClass) {
        route = findOrCreate(route);
        presenterRoutes.put(route, presenterClass);
    }

    public void registerRoute(Route route, Class<? extends Presenter> presenterClass, Class<? extends Command> commandClass) {
        route = findOrCreate(route);
        presenterRoutes.put(route, presenterClass);
        commandRoutes.put(route, commandClass);
    }

    public void registerRoute(Route route, Class<? extends Command> commandClass) {
        route = findOrCreate(route);
        commandRoutes.put(route, commandClass);
    }

    public Class<? extends Presenter> findPresenter(Navigable requestUri) {

        return presenterRoutes.get(findRoute(requestUri.getPath()));
    }

    public Class<? extends Command> findCommand(Navigable requestUri) {

        return commandRoutes.get(findRoute(requestUri.getPath()));
    }

    private Route findOrCreate(Route path) {
        for (Route route : routes) {
            if (route.match(path)) {
                return route;
            }
        }
        routes.add(path);
        return path;
    }

    private Route findRoute(String path) {
        for (Route route : routes) {
            if (route.match(path)) {
                return route;
            }
        }
        throw new NotFoundException("Cannot find route for " + path);
    }
}
