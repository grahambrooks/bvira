package bvira.framework;

public class UnrecoverableErrorException extends RuntimeException {
    public UnrecoverableErrorException(Exception e) {
        super(e);
    }
}
