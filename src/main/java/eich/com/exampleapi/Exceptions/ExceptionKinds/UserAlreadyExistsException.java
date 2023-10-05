package eich.com.exampleapi.Exceptions.ExceptionKinds;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
