package bvira.web;

import bvira.framework.RequestUri;
import bvira.framework.ResponseContext;
import bvira.framework.TemplateWriter;

import javax.servlet.http.HttpServletResponse;

public class CommandResponse extends WebResponseContext {
    public CommandResponse(HttpServletResponse response, ResourceContentType contentType) {
        super(response, contentType);
    }

    public static ResponseContext create(HttpServletResponse response, RequestUri requestUri) {
        return new CommandResponse(response, ResourceContentType.fromRequest(requestUri));
    }

    @Override
    public void writeTemplate(TemplateWriter template) {
        throw new RuntimeException("Commands cannot return data but should redirect to a presenter");
    }
}
