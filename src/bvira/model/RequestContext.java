package bvira.model;

import bvira.web.ContextPath;
import bvira.web.ContextRoot;
import bvira.web.Parameters;
import bvira.web.RequestUri;

public interface RequestContext {

    ContextRoot getContextRoot();

    ContextPath getContextPath();

    RequestUri getRequestUri();

    Parameters getParameters();

    String getParameter(Field identity);
}
