package bvira.components;

import bvira.web.Command;
import bvira.web.RequestContext;
import bvira.web.ResponseContext;

public class LoginCommand implements Command {
    public void execute(RequestContext requestContext, ResponseContext responseContext) {
        responseContext.redirectTo("/");
    }
}
