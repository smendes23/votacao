package br.com.sicredi.votacao.application.gateway;

public interface ValidadorCpfGateway {
    Boolean podeVotar(final String cpf);
}
