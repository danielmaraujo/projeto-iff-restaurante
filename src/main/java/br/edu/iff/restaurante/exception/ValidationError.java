package br.edu.iff.restaurante.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends GenericError{
    private List<ValidationErrorProperty> errors = new ArrayList();

    public ValidationError(LocalDateTime timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<ValidationErrorProperty> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorProperty> errors) {
        this.errors = errors;
    }
}
