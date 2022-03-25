package com.ishmamruhan.PracDay1.ErrorManagement;

import com.ishmamruhan.PracDay1.ErrorManagement.Errors.CustomizeException;
import com.ishmamruhan.PracDay1.ErrorManagement.Errors.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestException extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        RestErrorTemplate restError = new RestErrorTemplate();
        restError.setErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        restError.setErrorType("Bad Request");
        restError.setErrorMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(restError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        RestErrorTemplate restError = new RestErrorTemplate();
        restError.setErrorCode(HttpStatus.NOT_FOUND.value());
        restError.setErrorType("Not Found");
        restError.setErrorMessage(resourceNotFoundException.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restError);
    }

    @ExceptionHandler(CustomizeException.class)
    public ResponseEntity<Object> customException(CustomizeException customizeException){
        RestErrorTemplate restError = new RestErrorTemplate();

        restError.setErrorCode(customizeException.getError_code());
        restError.setErrorType(customizeException.getError_type());
        restError.setErrorMessage(customizeException.getMessage());

        return ResponseEntity.status(customizeException.getError_code()).body(restError);
    }

    //DataIntegrityViolationException

}
