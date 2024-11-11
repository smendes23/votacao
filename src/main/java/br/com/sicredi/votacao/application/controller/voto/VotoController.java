package br.com.sicredi.votacao.application.controller.voto;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.application.gateway.ValidadorCpfGateway;
import br.com.sicredi.votacao.application.gateway.VotoGateway;
import br.com.sicredi.votacao.application.mapper.VotoMapper;
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
@RequestMapping("/api/v1/votos")
public class VotoController {

    private final VotoGateway votoGateway;
    private final ValidadorCpfGateway validadorCpfGateway;

    @PostMapping
    public ResponseEntity<VotoDTO> receberVoto(@RequestBody VotoDTO dto) {
        return ofNullable(dto)
                .filter(cooperado -> validadorCpfGateway.podeVotar(cooperado.getCpf()))
                .map(VotoMapper::toEntity)
                .map(votoGateway::execute)
                .map(novoVoto -> new ResponseEntity<>(novoVoto, HttpStatus.CREATED))
                .orElseThrow();
    }
}
