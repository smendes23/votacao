package br.com.sicredi.votacao.application.controller.pauta;

import br.com.sicredi.votacao.application.controller.pauta.dto.request.PautaRequest;
import br.com.sicredi.votacao.application.controller.pauta.dto.response.PautaResponse;
import br.com.sicredi.votacao.application.gateway.PautaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/pauta")
public class PautaController {

    private final PautaGateway pautaGateway;

    @PostMapping
    public ResponseEntity<PautaResponse> cadastrarPauta(@RequestBody PautaRequest pauta) {
        return ofNullable(pauta)
                .map(pautaGateway::execute)
                .map(novaPauta -> new ResponseEntity<>(novaPauta, HttpStatus.CREATED))
                .orElseThrow();
    }
}