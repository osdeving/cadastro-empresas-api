package br.com.broadfactor.cadempresas.exceptions;

public class EmailNaoCorrespondeException extends RuntimeException {
    public EmailNaoCorrespondeException(String s) {
        super(s);
    }
}
