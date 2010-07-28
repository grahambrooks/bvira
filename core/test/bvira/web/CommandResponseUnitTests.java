package bvira.web;

import bvira.framework.RequestUri;
import bvira.framework.ResponseContext;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public class CommandResponseUnitTests {
    @Test(expected = RuntimeException.class)
    public void commandResponseDoesNotSupportTemplateWriting() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestUri requestUri = mock(RequestUri.class);
        ResponseContext responseContext = CommandResponse.create(response, requestUri);

        responseContext.writeTemplate(null);
    }
}
