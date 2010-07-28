package bvira.web;

import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WebResponseContextUnitTests {
    @Test(expected = RuntimeException.class)
    public void writeTemplateThrowsRuntimeExceptionForIOErrors() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        doThrow(new IOException("")).when(response).getWriter();
        WebResponseContext context = new WebResponseContext(response, ResourceContentType.TEXT);

        context.writeTemplate(null);
    }

    @Test
    public void redirectPassedOnToServletResponse() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        WebResponseContext context = new WebResponseContext(response, ResourceContentType.TEXT);

        context.redirectTo("Some path");
        verify(response).sendRedirect("Some path");
    }

    @Test
    public void redirectMakesResponseValid() throws IOException {
        WebResponseContext context = new WebResponseContext(mock(HttpServletResponse.class), ResourceContentType.TEXT);

        context.redirectTo("Some path");
        context.validateResponse();
    }


    @Test(expected = RuntimeException.class)
    public void runtimeExceptionThrownForInvalidActions() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        WebResponseContext context = new WebResponseContext(response, ResourceContentType.TEXT);

        context.validateResponse();
    }
}
