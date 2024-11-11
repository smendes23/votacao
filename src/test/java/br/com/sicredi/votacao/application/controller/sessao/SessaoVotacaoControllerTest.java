package br.com.sicredi.votacao.application.controller.sessao;

import br.com.sicredi.votacao.application.gateway.SessaoVotacaoGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SessaoVotacaoController.class)
public class SessaoVotacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoVotacaoGateway sessaoVotacaoGateway;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testAbrirSessaoWhenNullRequestThenReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/sessao/abrir")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(null)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}