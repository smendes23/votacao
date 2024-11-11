package br.com.sicredi.votacao.application.controller.resultado;

import br.com.sicredi.votacao.application.controller.resultado.dto.response.ResultadoResponse;
import br.com.sicredi.votacao.application.gateway.ContabilizacaoGateway;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ResultadoVotacaoController.class)
public class ResultadoVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContabilizacaoGateway contabilizacaoGateway;

    @Test
    public void testObterResultadoWhenIdPautaIsNullThenThrowNoSuchElementException() throws Exception {
        Long idPauta = null;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resultado/{idPauta}", idPauta))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testObterResultadoWhenIdPautaIsNotNullThenReturnResponseEntity() throws Exception {
        Long idPauta = 1L;
        ResultadoResponse resultadoResponse = ResultadoResponse.builder()
                .votosSim(10L)
                .votosNao(5L)
                .totalVotos(15L)
                .build();

        given(contabilizacaoGateway.execute(idPauta)).willReturn(resultadoResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/resultado/{idPauta}", idPauta))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.votos_sim").value(10L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.votos_nao").value(5L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total_votos").value(15L));
    }
}
