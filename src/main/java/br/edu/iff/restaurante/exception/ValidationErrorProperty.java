package br.edu.iff.restaurante.exception;

import java.io.Serializable;

public class ValidationErrorProperty implements Serializable {
    private String field;
    private String message;

    public ValidationErrorProperty(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
