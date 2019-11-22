package com.excelra.mvc.exception;

/**
 * <p>
 *  Service Exception
 * <p>
 *
 * @author venkateswarlu.s
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 25369827205008315L;

    public ServiceException(String message, Throwable tx) {
        super(message, tx);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable tx) {
        super(tx);
    }

}