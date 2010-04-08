package bvira.web;

import bvira.framework.RequestUri;
import bvira.framework.ResponseContext;

import javax.servlet.http.HttpServletResponse;

public class PresenterResponse extends WebResponseContext {
    protected PresenterResponse(HttpServletResponse response, ResourceContentType contentType) {
        super(response, contentType);
    }

    public static ResponseContext create(HttpServletResponse response, RequestUri requestUri) {
        return new PresenterResponse(response, ResourceContentType.fromRequest(requestUri));
    }
}
