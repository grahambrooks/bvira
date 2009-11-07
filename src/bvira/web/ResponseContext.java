package bvira.web;

import java.io.PrintWriter;

public interface ResponseContext {

    PrintWriter getWriter();

    void writeTemplate(TemplateWriter template);

    void redirectTo(String path);
}
