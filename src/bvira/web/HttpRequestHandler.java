package bvira.web;

import bvira.util.NotFoundException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpRequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebApplication application;

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        application = new WebApplication();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestUri requestUri = getRequestUri(request).without(request.getContextPath());
        try {
            RequestContext webRequest = WebRequestContext.create(request, requestUri);
            ResponseContext webResponse = WebResponseContext.create(response, requestUri);
            application.executePresenter(webRequest, webResponse);

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
}
