package com.excelra.mvc.exception;

/**
 * Tabular Service Exception
 *
 * @author Venkat Salagrama
 */
public class TabularServiceException extends RuntimeException {

    private static final long serialVersionUID = 25369827205008315L;

    public TabularServiceException(String message, Throwable tx) {
        super(message, tx);
    }

    public TabularServiceException(String message) {
        super(message);
    }

    public TabularServiceException(Throwable tx) {
        super(tx);
    }

}