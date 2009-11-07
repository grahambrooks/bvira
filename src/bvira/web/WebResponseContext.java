package bvira.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebResponseContext implements ResponseContext {
    private final HttpServletResponse response;
    private final ResourceContentType contentType;

    public WebResponseContext(HttpServletResponse response, ResourceContentType contentType) {
        this.response = response;
        this.contentType = contentType;
    }

    public static ResponseContext create(HttpServletResponse response, RequestUri requestUri) {
        return new WebResponseContext(response, ResourceContentType.fromRequest(requestUri));
    }

    public PrintWriter getWriter() {
        try {
            response.setContentType(contentType.toString());
            return response.getWriter();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTemplate(TemplateWriter template) {
        template.write(getWriter());
    }

    public void redirectTo(String path) {
        try {
            response.sendRedirect(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
