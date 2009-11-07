package bvira.web;

public interface Command {
    void execute(RequestContext requestContext, ResponseContext responseContext);
}
