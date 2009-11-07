package bvira.web;

import java.io.PrintWriter;

public interface TemplateFactory {

    TemplateWriter templateFor(Class<? extends Presenter> aClass);

    void write(Class<? extends Presenter> aClass, PrintWriter writer, Object... attributes);
}
