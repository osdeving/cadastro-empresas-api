package br.com.broadfactor.cadempresas.exceptions;

public class CnpjJaExisteException extends RuntimeException {
    public CnpjJaExisteException(String message) {
        super(message);
    }
}
