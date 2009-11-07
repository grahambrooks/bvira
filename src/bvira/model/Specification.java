package bvira.model;

import bvira.util.Maps;
import bvira.web.Parameters;

import java.util.Map;

public class Specification {

    private final Map<Field, String> fields = Maps.create();

    public Specification() {
    }

    public Specification(Parameters parameters, Field... fields) {
        for (Field field : fields) {
            String value = parameters.get(field.getParameterName());
            this.fields.put(field, value);
        }
    }

    protected void field(Field key, String value) {
        fields.put(key, value);
    }

    public String get(Field key) {
        return fields.get(key);
    }

    public boolean contains(Field key) {
        return fields.containsKey(key);
    }
}
