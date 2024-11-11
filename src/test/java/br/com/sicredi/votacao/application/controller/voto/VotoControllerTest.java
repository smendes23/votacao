package br.com.sicredi.votacao.application.controller.voto;

import br.com.sicredi.votacao.application.controller.voto.dto.VotoDTO;
import br.com.sicredi.votacao.application.gateway.ValidadorCpfGateway;
import br.com.sicredi.votacao.application.gateway.VotoGateway;
import br.com.sicredi.votacao.domain.enums.Decisao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(VotoController.class)
public class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VotoGateway votoGateway;

    @MockBean
    private ValidadorCpfGateway validadorCpfGateway;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testReceberVotoWhenValidVotoDTOThenReturnCreated() throws Exception {
        VotoDTO votoDTO = VotoDTO.builder()
                .idPauta(1L)
                .cpf("12345678901")
                .voto(Decisao.SIM)
                .build();

        Mockito.when(validadorCpfGateway.podeVotar(votoDTO.getCpf())).thenReturn(true);
        Mockito.when(votoGateway.execute(Mockito.any())).thenReturn(votoDTO);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(votoDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(votoDTO)));
    }

    @Test
    public void testReceberVotoWhenNullVotoDTOThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/votos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
