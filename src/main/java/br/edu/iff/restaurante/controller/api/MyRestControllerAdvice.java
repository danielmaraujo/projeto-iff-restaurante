package br.edu.iff.restaurante.controller.api;

import br.edu.iff.restaurante.exception.GenericError;
import br.edu.iff.restaurante.exception.NotFoundException;
import br.edu.iff.restaurante.exception.ValidationError;
import br.edu.iff.restaurante.exception.ValidationErrorProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class MyRestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity genericError(Exception ex, HttpServletRequest req){
        GenericError error = new GenericError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage(),
                req.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundError(NotFoundException ex, HttpServletRequest req){
        GenericError error = new GenericError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                req.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity validationError(ConstraintViolationException ex, HttpServletRequest req){
        ValidationError error = new ValidationError(LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validação",
                req.getRequestURI());

        for(ConstraintViolation cv : ex.getConstraintViolations()){
            ValidationErrorProperty p = new ValidationErrorProperty(cv.getPropertyPath().toString(), cv.getMessage());
            error.getErrors().add(p);
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validationError(MethodArgumentNotValidException ex, HttpServletRequest req){
        ValidationError error = new ValidationError(LocalDateTime.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                HttpStatus.UNPROCESSABLE_ENTITY.name(),
                "Erro de validação",
                req.getRequestURI());

        for(FieldError fe : ex.getBindingResult().getFieldErrors()){
            ValidationErrorProperty p = new ValidationErrorProperty(fe.getField().toString(), fe.getDefaultMessage());
            error.getErrors().add(p);
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }


}
