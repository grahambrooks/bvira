package bvira.web;

import bvira.model.Field;
import bvira.model.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class WebRequestContext implements RequestContext {
    private final ContextRoot contextRoot;
    private final ContextPath contextPath;
    private final RequestUri requestUri;
    private final Parameters parameters;

    public WebRequestContext(ContextPath contextPath, ContextRoot contextRoot, RequestUri requestUri, Parameters parameters) {
        this.contextPath = contextPath;
        this.contextRoot = contextRoot;
        this.requestUri = requestUri;
        this.parameters = parameters;
    }

    public static RequestContext create(HttpServletRequest request, RequestUri requestUri) {
        ContextRoot contextRoot = new ContextRoot(request.getServerName(), request.getServerPort());
        ContextPath contextPath = new ContextPath(request.getContextPath());
        Parameters parameters = new Parameters(request.getParameterMap());
        return new WebRequestContext(contextPath, contextRoot, requestUri, parameters);
    }

    public ContextPath getContextPath() {
        return contextPath;
    }

    public ContextRoot getContextRoot() {
        return contextRoot;
    }

    public RequestUri getRequestUri() {
        return requestUri;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public String getParameter(Field field) {
        return parameters.get(field.getParameterName());
    }
}
