package bvira.web;

import bvira.model.Field;

public interface RequestContext {

    ContextRoot getContextRoot();

    ContextPath getContextPath();

    RequestUri getRequestUri();

    Parameters getParameters();

    String getParameter(Field identity);
}
