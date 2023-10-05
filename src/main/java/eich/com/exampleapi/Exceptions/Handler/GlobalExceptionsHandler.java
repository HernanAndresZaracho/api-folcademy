package eich.com.exampleapi.Exceptions.Handler;

import eich.com.exampleapi.Exceptions.Dtos.ErrorMessageDTO;
import eich.com.exampleapi.Exceptions.ExceptionKinds.*;
import eich.com.exampleapi.Exceptions.Dtos.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice

public class GlobalExceptionsHandler {
    @ExceptionHandler(ContactoExistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> contactoAlreadyExistsHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ContactoBadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> contactoBadRequestHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactoInexistenteException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> contactoNotFoundHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(ContactoInexistenteException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ErrorResponseDTO handleContactoInexistenteException(ContactoInexistenteException e) {
//        return new ErrorResponseDTO(e.getMessage(), HttpStatus.NOT_FOUND.value());
//    }





    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> defaultErrorHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> notFoundHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> userAlreadyExistsHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserBadRequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessageDTO> badRequestHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMessageDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
