package bvira.framework;

import bvira.util.Maps;

import java.util.Map;

public class Parameters {

    private final Map<String, String> parameters;

    public Parameters(Map parameterMap) {
        parameters = Maps.create();

        for (Object o : parameterMap.entrySet()) {
            parameters.put((String) ((Map.Entry<Object, Object>) o).getKey(), (String) ((Map.Entry<Object, Object>) o).getValue());
        }
        for (Object key : parameterMap.keySet()) {
            String value = (String) parameterMap.get(key);
            parameters.put((String) key, value);
        }
    }

    public String get(String parameterName) {
        return parameters.get(parameterName);
    }
}
