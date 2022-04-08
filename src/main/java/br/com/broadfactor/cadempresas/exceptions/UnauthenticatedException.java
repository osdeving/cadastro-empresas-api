package br.com.broadfactor.cadempresas.exceptions;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException(String s) {
        super(s);
    }
}
