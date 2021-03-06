package ag.boersego.bgjs;

/**
 * Created by martin on 20.10.17.
 */

public class V8Exception extends RuntimeException {
    public V8Exception(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Retrieves the error thrown in JS
     * most likely an instance of JNIV8GenericObject if it was an `Error`
     * Could also be any other wrapped JS value (primitive, function, Java/V8 tuple) or null
     */
    Object getV8Exception() {
        Throwable cause = this.getCause();
        if(cause instanceof V8JSException) {
            return ((V8Exception)cause).getV8Exception();
        }
        return null;
    }

    /**
     * checks if this exception was actually caused by JS, or by native/Java code
     */
    boolean wasCausedByJS() {
        Throwable cause = this.getCause();
        return cause != null && cause.getCause() == null;
    }
}
