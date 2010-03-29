package bvira.components;

import bvira.framework.Command;
import bvira.framework.RequestContext;
import bvira.framework.ResponseContext;

public class LoginCommand implements Command {
    public void execute(RequestContext requestContext, ResponseContext responseContext) {
        responseContext.redirectTo("/");
    }
}
