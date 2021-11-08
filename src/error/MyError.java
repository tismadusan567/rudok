package error;

public class MyError {

    private final String message;
    private final ErrorFactory.ErrorType errorType;

    public MyError(String message, ErrorFactory.ErrorType errorType) {
        this.message = message;
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return message;
    }
}
