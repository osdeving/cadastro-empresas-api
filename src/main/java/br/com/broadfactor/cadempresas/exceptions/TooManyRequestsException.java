package br.com.broadfactor.cadempresas.exceptions;

public class TooManyRequestsException extends RuntimeException {
    public TooManyRequestsException(String s) {
        super(s);
    }
}
