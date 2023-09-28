package eich.com.exampleapi.Exceptions.ExceptionKinds;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){ super(message); }
}
