package bvira.framework;

import java.io.Writer;

public interface TemplateWriter {
    void setProperty(String propertyName, Object propertyValue);

    void write(Writer writer);
}
