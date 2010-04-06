package bvira.framework;

public interface ResponseContext {

    void writeTemplate(TemplateWriter template);

    void redirectTo(String path);
}
