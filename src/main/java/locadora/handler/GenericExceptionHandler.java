package locadora.handler;

import locadora.domain.dto.GenericExceptionDto;
import locadora.handler.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<GenericExceptionDto> entityNotFound(EntidadeNaoEncontradaException exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().body(new GenericExceptionDto(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericExceptionDto> handleValidationExceptions(MethodArgumentNotValidException exception) {
        System.out.println(exception.getMessage());
        String errorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(new GenericExceptionDto(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericExceptionDto> handleException(Exception exception) {
        System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().body(new GenericExceptionDto(exception.getMessage()));
    }
}
