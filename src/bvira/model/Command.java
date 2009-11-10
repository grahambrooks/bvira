package bvira.model;

public interface Command {
    void execute(RequestContext requestContext, ResponseContext responseContext);
}
