package com.excelra.mvc.model.error;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiError {
    private HttpStatus status;
    private String message;
    private List<String> errors;
    private Object exceptionObject;

    public ApiError() {}

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public ApiError(HttpStatus status, String message, String error, Object exceptionObject) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
        this.exceptionObject=exceptionObject;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Object getExceptionObject() {
        return exceptionObject;
    }

    public void setExceptionObject(Object exceptionObject) {
        this.exceptionObject = exceptionObject;
    }

}

