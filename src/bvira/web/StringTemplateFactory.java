package bvira.web;

import bvira.model.Presenter;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;

import java.io.IOException;
import java.io.Writer;

public class StringTemplateFactory implements TemplateFactory {
    StringTemplateGroup group;
    public StringTemplateFactory() {
        group = new StringTemplateGroup("components");
    }
    public TemplateWriter templateFor(Class<? extends Presenter> aClass) {
        String templateName = nameForClass(aClass);
        final StringTemplate stringTemplate = group.getInstanceOf(templateName);
        return new TemplateWriter() {
            public void setProperty(String propertyName, Object propertyValue) {
                stringTemplate.setAttribute(propertyName, propertyValue);
            }

            public void write(Writer writer) {
                try {
                    writer.write(stringTemplate.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private String nameForClass(Class<? extends Presenter> aClass) {
        String className = aClass.getSimpleName();
        return className.replaceAll("Presenter", "");
    }
}
