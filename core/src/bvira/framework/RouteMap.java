package bvira.framework;

public interface RouteMap {

    Class<? extends Presenter> findPresenter(Navigable navigable);

    void registerPresenter(Route route, Class<? extends Presenter> presenterClass);

    void registerPresenter(Route route, Class<? extends Presenter> presenterClass, Class<? extends Command> commandClass);

    Class<? extends Command> findCommand(Navigable navigable);
}
