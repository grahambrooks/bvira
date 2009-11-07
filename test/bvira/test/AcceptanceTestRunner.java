package bvira.test;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runner.notification.RunNotifier;

public class AcceptanceTestRunner  extends BlockJUnit4ClassRunner {

    public AcceptanceTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        WebEnvironment.setInstance(new AcceptanceTestWebEnvironment());
        super.runChild(method, notifier);
    }
}
