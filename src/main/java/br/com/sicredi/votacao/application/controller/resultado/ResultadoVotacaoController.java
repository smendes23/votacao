package br.com.sicredi.votacao.application.controller.resultado;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.application.gateway.ContabilizacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/resultado")
public class ResultadoVotacaoController {

    private final ContabilizacaoGateway contabilizacaoGateway;

    @GetMapping("/{idPauta}")
    public ResponseEntity<ResultadoResponse> obterResultado(@PathVariable Long idPauta) {
        return Optional.ofNullable(idPauta)
                .map(contabilizacaoGateway::execute)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }
}
