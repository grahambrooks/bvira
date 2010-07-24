package bvira.framework;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParametersUnitTests {
    @Test
    public void parametersInitialisedFromObjectMap() {
        Map<Object, Object> rawParameters = new HashMap<Object, Object>();
        rawParameters.put("foo", "bar");

        Parameters parameters = new Parameters(rawParameters);

        assertThat(parameters.get("foo"), is("bar"));
    }
}
