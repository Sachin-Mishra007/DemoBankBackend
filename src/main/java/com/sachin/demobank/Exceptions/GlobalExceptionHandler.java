package com.sachin.demobank.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req)
    {
        return buildResponse(ex.getMessage(),req.getRequestURI(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceExcpetion.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateResourceExcpetion ex, HttpServletRequest req)
    {
        return buildResponse(ex.getMessage(),req.getRequestURI(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidDataException ex, HttpServletRequest req)
    {
        return buildResponse(ex.getMessage(),req.getRequestURI(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(OperationNotAllowedException.class)
    public ResponseEntity<ErrorResponse> handleNotAllowed(OperationNotAllowedException ex, HttpServletRequest req)
    {
        return buildResponse(ex.getMessage(),req.getRequestURI(), HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalance(InsufficientBalanceException ex)
    {
        return buildResponse(ex.getMessage(), null, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccountNotActiveException.class)
    public ResponseEntity<ErrorResponse> hanldeInactiveAccount(AccountNotActiveException ex)
    {
        return buildResponse(ex.getMessage(),  null,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> hanldeInactiveAccount(IllegalArgumentException ex,HttpServletRequest req)
    {
        return buildResponse(ex.getMessage(),  req.getRequestURI(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleValidationErrors(
        MethodArgumentNotValidException ex,
        HttpServletRequest req) {

    String errorMessage = ex.getBindingResult()
                            .getFieldErrors()
                            .stream()
                            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                            .findFirst()
                            .orElse("Invalid input data");

    
    return buildResponse(errorMessage, req.getRequestURI(), HttpStatus.BAD_REQUEST);
}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest req)
    {
        return buildResponse("Internal server Error",req.getRequestURI(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    private ResponseEntity<ErrorResponse> buildResponse(String msg,String path,HttpStatus status)
    {
        ErrorResponse errorResponse=new ErrorResponse(
            LocalDateTime.now(),
            msg,path,status.value()
        );
        return new ResponseEntity<>(errorResponse,status);
    }

}
