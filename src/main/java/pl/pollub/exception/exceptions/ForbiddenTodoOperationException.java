package pl.pollub.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenTodoOperationException extends RuntimeException {
    public ForbiddenTodoOperationException() {
    }

    public ForbiddenTodoOperationException(String message) {
        super(message);
    }

    public ForbiddenTodoOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenTodoOperationException(Throwable cause) {
        super(cause);
    }
}
