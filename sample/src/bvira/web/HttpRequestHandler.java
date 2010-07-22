package bvira.web;

import bvira.components.HomePageComponent;
import bvira.components.LoginComponent;
import bvira.components.OfficeComponent;
import bvira.framework.Component;
import bvira.framework.RequestContext;
import bvira.framework.RequestUri;
import bvira.framework.ResponseContext;
import bvira.persistance.StubOfficeFinder;
import bvira.util.DefaultContainer;
import bvira.util.NotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private transient WebApplication application;


    public static final List<Class> services = new ArrayList<Class>() {{
            add(StubOfficeFinder.class);
    }};

    private static final List<Component> components = new ArrayList<Component>() {{
        add(new HomePageComponent());
        add(new LoginComponent());
        add(new OfficeComponent());
    }};


    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);


        application = new WebApplication(components, new DefaultContainer(), new StringTemplateFactory(), services);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestUri requestUri = getRequestUri(request).without(request.getContextPath());
        try {
            RequestContext webRequest = WebRequestContext.create(request, requestUri);
            ResponseContext webResponse = PresenterResponse.create(response, requestUri);
            application.executePresenter(webRequest, webResponse);
            webResponse.validateResponse();
        } catch (RuntimeException e) {
            error(requestUri, response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestUri requestUri = getRequestUri(request).without(request.getContextPath());
        try {
            RequestContext webRequest = WebRequestContext.create(request, requestUri);
            ResponseContext webResponse = CommandResponse.create(response, requestUri);
            application.executeCommand(webRequest, webResponse);
            webResponse.validateResponse();
        } catch (RuntimeException e) {
            error(requestUri, response, e);
        }
    }

    private static RequestUri getRequestUri(HttpServletRequest request) {
        String requestUri = (String) request.getAttribute("javax.servlet.include.request_uri");
        String query = (String) request.getAttribute("javax.servlet.include.query_string");
        if (requestUri == null) {
            requestUri = request.getRequestURI();
            query = request.getQueryString();
        }
        return new RequestUri(requestUri, query);
    }

    private void error(RequestUri requestUri, HttpServletResponse response, RuntimeException error) throws IOException {
        if (error instanceof NotFoundException) {
            log(requestUri + " --> " + error);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            log(requestUri.toString(), error);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public static Iterable<Component> getComponents() {
        return components;
    }

    public static Iterable<Class> getServices() {
        return services;
    }
}
