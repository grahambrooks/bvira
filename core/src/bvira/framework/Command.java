package bvira.framework;

public interface Command {
    void execute(RequestContext requestContext, ResponseContext responseContext);
}
