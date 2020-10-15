package com.profectus.retail.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(NoSuchElementException.class)
   public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
       String error = "No entry for the passed in parameter";
       return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
   }
	
   @ExceptionHandler(NoDataException.class)
   public ResponseEntity<Object> handleArithmeticException(NoDataException ex) {
       String error = "No purchase or sale data could be found to calculate the profit. Please add";
       return buildResponseEntity(new APIError(HttpStatus.BAD_REQUEST, error, ex));
   }
   
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handleExceptions(Exception ex) {
       String error = "Some runtime exception";
       return buildResponseEntity(new APIError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
   }
   
   
   private ResponseEntity<Object> buildResponseEntity(APIError apiError) {
       return new ResponseEntity<>(apiError, apiError.getStatus());
       
   }

   //other exception handlers below

}
