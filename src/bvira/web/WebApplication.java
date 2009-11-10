package bvira.web;

import bvira.components.HomePageComponent;
import bvira.components.LoginComponent;
import bvira.components.OfficeComponent;
import bvira.model.*;
import bvira.util.Container;
import bvira.persistance.StubOfficeFinder;

public class WebApplication {
    private final Container container = new Container();
    private final RouteMap routeMap = new WebRouteMap();

    private static Component[] components = {
        new HomePageComponent(),
        new LoginComponent(),
        new OfficeComponent(),
    };

    public WebApplication() {
        this.container.register(this);
        this.container.register(new StringTemplateFactory());
        this.container.register(new StubOfficeFinder());

        for (Component component : components) {
            component.registerClasses(container);
            component.registerRoutes(routeMap);
        }
    }

    public void executePresenter(RequestContext requestContext, ResponseContext responseContext) {
        Container requestContainer = new Container(container);

        Class<? extends Presenter> presenterClass = routeMap.findPresenter(requestContext.getRequestUri());
        Presenter presenter = requestContainer.getInstance(presenterClass);
        presenter.present(requestContext, responseContext);
    }

    public void executeCommand(RequestContext requestContext, ResponseContext responseContext) {
        Container requestContainer = new Container(container);

        Class<? extends Command> commandClass = routeMap.findCommand(requestContext.getRequestUri());
        Command command = requestContainer.getInstance(commandClass);
        command.execute(requestContext, responseContext);
    }
}
