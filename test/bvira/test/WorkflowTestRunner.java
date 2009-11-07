package bvira.test;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class WorkflowTestRunner extends BlockJUnit4ClassRunner {

    public WorkflowTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(FrameworkMethod method, RunNotifier notifier) {
        WebEnvironment testWebEnvironment = new WorkflowTestWebEnvironment();
        testWebEnvironment.start();
        WebEnvironment.setInstance(testWebEnvironment);
        try {
            super.runChild(method, notifier);
        } finally {
            testWebEnvironment.stop();
        }
    }
}
