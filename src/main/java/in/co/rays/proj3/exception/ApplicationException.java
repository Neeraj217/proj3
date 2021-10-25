package in.co.rays.proj3.exception;

/**
 * ApplicationException is propagated from Service classes when an business
 * logic exception occurred.
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */
public class ApplicationException extends Exception {

    /**
     * @param msg
     * Error message
     */
    public ApplicationException(String msg) {
        super(msg);
    }
}
