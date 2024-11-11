package br.com.sicredi.votacao.application.mapper;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.domain.enums.Decisao;
import br.com.sicredi.votacao.domain.model.Voto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class VotoMapperTest {

    @Test
    public void testToEntityWhenVotoDTOIsValidThenReturnVoto() {
        VotoDTO dto = VotoDTO.builder()
                .idPauta(1L)
                .cpf("12345678901")
                .voto(Decisao.SIM)
                .build();

        Voto entity = VotoMapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getIdPauta()).isEqualTo(dto.getIdPauta());
        assertThat(entity.getCpf()).isEqualTo(dto.getCpf());
        assertThat(entity.getVoto()).isEqualTo(dto.getVoto());
    }

    @Test
    public void testToDtoWhenVotoIsValidThenReturnVotoDTO() {
        Voto entity = Voto.builder()
                .idPauta(1L)
                .cpf("12345678901")
                .voto(Decisao.SIM)
                .build();

        VotoDTO dto = VotoMapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getIdPauta()).isEqualTo(entity.getIdPauta());
        assertThat(dto.getCpf()).isEqualTo(entity.getCpf());
        assertThat(dto.getVoto()).isEqualTo(entity.getVoto());
    }
}
