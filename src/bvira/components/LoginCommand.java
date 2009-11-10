package bvira.components;

import bvira.model.Command;
import bvira.model.RequestContext;
import bvira.model.ResponseContext;

public class LoginCommand implements Command {
    public void execute(RequestContext requestContext, ResponseContext responseContext) {
        responseContext.redirectTo("/");
    }
}
