package bvira.web;

import bvira.framework.ResponseContext;
import bvira.framework.TemplateWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

class WebResponseContext implements ResponseContext {
    private final HttpServletResponse response;
    private final ResourceContentType contentType;
    private boolean validResponse;

    protected WebResponseContext(HttpServletResponse response, ResourceContentType contentType) {
        this.response = response;
        this.contentType = contentType;
        this.validResponse = false;
    }

    private PrintWriter getWriter() {
        try {
            response.setContentType(contentType.toString());
            return response.getWriter();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTemplate(TemplateWriter template) {
        template.write(getWriter());
        validResponse = true;
    }

    public void redirectTo(String path) {
        try {
            response.sendRedirect(path);
            validResponse = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateResponse() {
        if (!validResponse) {
            throw new RuntimeException("Invalid response to request. Presenters must return a redirect or generate content. Commands must return a redirect URL");
        }
    }
}
