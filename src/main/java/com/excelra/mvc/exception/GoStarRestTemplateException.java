package com.excelra.mvc.exception;

public class GoStarRestTemplateException extends RuntimeException{

    private static final long serialVersionUID = 1186449842830771229L;

    public GoStarRestTemplateException(String message, Throwable tx) {
        super(message, tx);
    }

    public GoStarRestTemplateException(String message) {
        super(message);
    }

    public GoStarRestTemplateException(Throwable tx) {
        super(tx);
    }

}