package bvira.test.abstraction;

public class TestFrameworkException extends RuntimeException {
    public TestFrameworkException(Exception e) {
        super(e);
    }

    public TestFrameworkException(String message, Exception e) {
        super(message, e);
    }
}
