package bvira.web;

import bvira.framework.Command;
import bvira.framework.Component;
import bvira.framework.Presenter;
import bvira.framework.RequestContext;
import bvira.framework.ResponseContext;
import bvira.framework.RouteMap;
import bvira.framework.TemplateFactory;
import bvira.util.Container;

public class WebApplication {
    private final Container container;
    private final RouteMap routeMap;

    public WebApplication(Component[] components, Container container, TemplateFactory templateFactory, Class[] services) {
        this.container = container;
        this.routeMap = new WebRouteMap();
        this.container.register(this);
        this.container.register(templateFactory);
        for (Class service : services) {
            this.container.register(service);
        }
//        this.container.register(new StubOfficeFinder());

        for (Component component : components) {
            component.registerClasses(container);
            component.registerRoutes(routeMap);
        }
    }

    public void executePresenter(RequestContext requestContext, ResponseContext responseContext) {
        Container requestContainer = container.transientContainer();

        Class<? extends Presenter> presenterClass = routeMap.findPresenter(requestContext.getRequestUri());
        Presenter presenter = requestContainer.getInstance(presenterClass);
        presenter.present(requestContext, responseContext);
    }

    public void executeCommand(RequestContext requestContext, ResponseContext responseContext) {
        Container requestContainer = container.transientContainer();

        Class<? extends Command> commandClass = routeMap.findCommand(requestContext.getRequestUri());
        Command command = requestContainer.getInstance(commandClass);
        command.execute(requestContext, responseContext);
    }
}
