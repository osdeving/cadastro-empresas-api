package br.com.broadfactor.cadempresas.exceptions;

public class PermissionDeniedException extends RuntimeException {
    public PermissionDeniedException(String s) {
        super(s);
    }
}
