package bvira.framework;

import bvira.util.Maps;

import java.util.Map;

public class Parameters {

    private final Map<String, String> parameters = Maps.create();

    public Parameters() {
    }

    public Parameters(Map parameterMap) {
        for (Object key : parameterMap.keySet()) {
            String value = (String) parameterMap.get(key);
            parameters.put((String) key, value);
        }
    }

    public String get(String parameterName) {
        return parameters.get(parameterName);
    }
}
