package br.com.broadfactor.cadempresas.exceptions;

public class GatewayTimeoutException extends RuntimeException {
    public GatewayTimeoutException(String s) {
        super(s);
    }
}
