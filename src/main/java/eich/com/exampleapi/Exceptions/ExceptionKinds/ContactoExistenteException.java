package eich.com.exampleapi.Exceptions.ExceptionKinds;

public class ContactoExistenteException extends RuntimeException {
    public ContactoExistenteException(String message) {
        super(message);
    }
}
