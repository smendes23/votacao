package br.com.sicredi.votacao.application.controller.sessao;

import br.com.sicredi.votacao.application.controller.sessao.dto.request.SessaoRequest;
import br.com.sicredi.votacao.application.controller.sessao.dto.response.SessaoResponse;
import br.com.sicredi.votacao.application.gateway.SessaoVotacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessao")
@RequiredArgsConstructor
public class SessaoVotacaoController {

    private final SessaoVotacaoGateway sessaoVotacaoGateway;

    @PostMapping("/abrir")
    public ResponseEntity<SessaoResponse> abrirSessao(@RequestBody SessaoRequest request) {
        return Optional.ofNullable(request)
                .map(sessaoVotacaoGateway::execute)
                .map(sessao -> new ResponseEntity<>(sessao, HttpStatus.CREATED))
                .orElseThrow();
    }
}