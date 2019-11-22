package com.excelra.mvc.exception;

public class GoStarSqlException extends RuntimeException{

    private static final long serialVersionUID = 1186449842830771229L;

    public GoStarSqlException(String message, Throwable tx) {
        super(message, tx);
    }

    public GoStarSqlException(String message) {
        super(message);
    }

    public GoStarSqlException(Throwable tx) {
        super(tx);
    }

}
