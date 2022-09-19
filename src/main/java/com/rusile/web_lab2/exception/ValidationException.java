package com.rusile.web_lab2.exception;

import lombok.AllArgsConstructor;

import java.util.Map;

public class ValidationException extends IllegalArgumentException {
    private  Map<String, String> errors;

    private String message;

    public ValidationException(String message) {
        this.message = message;
    }

    public ValidationException(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        if (errors != null) {
            StringBuilder builder = new StringBuilder();
            errors.forEach((field, message) -> builder.append("Incorrect ").append(field)
                    .append(": ").append(message)
                    .append("\n"));
            return builder.toString();
        } else {
            return message;
        }
    }
}
