package com.example.springboot.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResult errorResult = new ErrorResult();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResult.getFieldErrors().add(new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return errorResult;
    }

    @Getter
    @NoArgsConstructor
    public static class ErrorResult {
        private List<FieldValidationError> fieldErrors = new ArrayList<>();

        public ErrorResult(String field, String message) {
            this.fieldErrors.add(new FieldValidationError(field, message));
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    private static class FieldValidationError {
        private String field;
        private String message;
    }


}
