package pl.pollub.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.pollub.exception.exceptions.ElementNotFoundException;
import pl.pollub.exception.exceptions.ForbiddenTodoOperationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = ElementNotFoundException.class)
    protected ResponseEntity<Object> handleElementNotFound(RuntimeException ex, WebRequest request){
        final String bodyOfResponse = "Element wasn't found";
        return handleExceptionInternal(ex,bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(value = ForbiddenTodoOperationException.class)
    protected ResponseEntity<Object> handleForbiddenTodoOperation(RuntimeException ex, WebRequest request){
        final String bodyOfResponse = "Cannot perform this operation on specified todo";
        return handleExceptionInternal(ex,bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN,request);
    }
}
