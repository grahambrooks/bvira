package bvira.web;

import bvira.framework.RequestUri;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

public class PresenterResponseUnitTests {
    @Test
    public void presenterResponseWrapsResponseToImproveSemantics() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestUri requestUri = mock(RequestUri.class);
        PresenterResponse.create(response, requestUri);
    }
}
