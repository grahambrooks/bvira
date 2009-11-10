package bvira.model;

import bvira.web.TemplateWriter;

import java.io.PrintWriter;

public interface ResponseContext {

    PrintWriter getWriter();

    void writeTemplate(TemplateWriter template);

    void redirectTo(String path);
}
