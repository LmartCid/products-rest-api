package com.luis.productosapi.exceptions;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> showValidErrors(MethodArgumentNotValidException ex) {
        Map<String, String> validErrors = new HashMap<>();
        List<FieldError> errorsList = ex.getBindingResult().getFieldErrors();

        for(FieldError err: errorsList) {
            validErrors.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(validErrors);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> showValidatedErrors(ConstraintViolationException ex) {
        Map<String, String> validatedErrorsMap = new HashMap<>();
        var validatedErrorsSet = ex.getConstraintViolations();
        validatedErrorsSet.forEach(err -> validatedErrorsMap.put(err.getPropertyPath().toString(), err.getMessage()));
        return ResponseEntity.badRequest().body(validatedErrorsMap);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> notFoundProduct(ProductNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> categoryNotFound(CategoryNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
