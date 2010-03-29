package bvira.framework;


public interface RequestContext {

    ContextRoot getContextRoot();

    ContextPath getContextPath();

    RequestUri getRequestUri();

    Parameters getParameters();

    String getParameter(ParameterName name);
}
