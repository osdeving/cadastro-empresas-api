package br.com.broadfactor.cadempresas.exceptions;

public class EmailJaExisteException extends RuntimeException {
    public EmailJaExisteException(String message) {
        super(message);
    }
}
