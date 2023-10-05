package eich.com.exampleapi.Exceptions.ExceptionKinds;

public class ContactoInexistenteException extends RuntimeException{
    public ContactoInexistenteException(String message){ super(message); }
}
