package br.com.sicredi.votacao.application.exception;

public class SessaoException extends RuntimeException {

    public SessaoException() {
        super("Error! A sessao foi encerrada.");
    }
}
