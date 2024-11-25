package com.example.TrabalhoFDS.testes.testesDeSistema;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AssinaturaControllerSystemTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Configuração inicial, se necessário
    }

    @Test
    void listarClientes_DeveRetornarListaDeClientes() throws Exception {
        mockMvc.perform(get("/servcad/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].nome", not(emptyOrNullString())));
    }

    @Test
    void listarAplicativos_DeveRetornarListaDeAplicativos() throws Exception {
        mockMvc.perform(get("/servcad/aplicativos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].nome", not(emptyOrNullString())));
    }

    @Test
    void cadastrarAssinatura_DeveRetornarNovaAssinatura() throws Exception {
        String criarAssinaturaJson = """
                {
                    "clienteId": 1,
                    "aplicativoId": 2
                }
                """;

        mockMvc.perform(post("/servcad/assinaturas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(criarAssinaturaJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo", notNullValue()))
                .andExpect(jsonPath("$.status", is("ATIVA")));
    }

    @Test
    void atualizarCustoAplicativo_DeveRetornarAplicativoAtualizado() throws Exception {
        String atualizarCustoJson = """
                {
                    "custo": 49.99
                }
                """;

        mockMvc.perform(post("/servcad/aplicativos/custo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(atualizarCustoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.custo", is(49.99)));
    }

    @Test
    void listarAssinaturasPorTipo_DeveRetornarListaDeAssinaturas() throws Exception {
        mockMvc.perform(get("/servcad/assinaturas/mensal"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].status", not(emptyOrNullString())));
    }

    @Test
    void listarAssinaturasCliente_DeveRetornarListaDeAssinaturasDoCliente() throws Exception {
        mockMvc.perform(get("/servcad/clientes/1/assinaturas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].clienteId", is(1)));
    }

    @Test
    void listarAssinaturasPorAplicativo_DeveRetornarListaDeAssinaturasDoAplicativo() throws Exception {
        mockMvc.perform(get("/servcad/aplicativos/1/assinaturas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[0].aplicativoId", is(1)));
    }
}
