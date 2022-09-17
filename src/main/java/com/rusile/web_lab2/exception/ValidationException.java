package com.rusile.web_lab2.exception;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ValidationException extends IllegalArgumentException {
    private final Map<String, String> errors;

    @Override
    public String getMessage() {
        StringBuilder builder = new StringBuilder();
        errors.forEach((field, message) -> builder.append("Incorrect ").append(field)
                .append(": ").append(message));
        return builder.toString();
    }
}
