package eich.com.exampleapi.Exceptions.ExceptionKinds;

public class ContactoBadRequestException extends RuntimeException{
    public ContactoBadRequestException(String message){ super(message); }
}
