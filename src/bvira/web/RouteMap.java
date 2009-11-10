package bvira.web;

import bvira.model.Command;
import bvira.model.Presenter;

public interface RouteMap {

    Class<? extends Presenter> findPresenter(RequestUri requestUri);

    void registerPresenter(Route route, Class<? extends Presenter> presenterClass);
    void registerPresenter(Route route, Class<? extends Presenter> presenterClass, Class<? extends Command> commandClass);

    Class<? extends Command> findCommand(RequestUri requestUri);
}
