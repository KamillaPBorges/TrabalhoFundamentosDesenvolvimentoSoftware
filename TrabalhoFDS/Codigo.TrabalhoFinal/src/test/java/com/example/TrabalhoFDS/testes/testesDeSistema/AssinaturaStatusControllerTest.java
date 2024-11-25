package com.example.TrabalhoFDS.testes.testesDeSistema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssinaturaStatusControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Teste de valor limite: Pagamento correto no valor exato do custo
    @Test
    void registrarPagamento_PagamentoExato_DeveRetornarPagamentoOk() throws Exception {
        String url = "http://localhost:" + port + "/registrarpagamento" +
                "?dia=24&mes=11&ano=2024&codass=1&valorPago=29.99"; // Valor exato do custo

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode jsonResponse = objectMapper.readTree(response.getBody());

        assertThat(jsonResponse.get("status").asText()).isEqualTo("PAGAMENTO_OK");
        assertThat(jsonResponse.get("valorEstornado").isNull()).isTrue();

        ZonedDateTime dataPagamento = ZonedDateTime.parse(jsonResponse.get("dataPagamento").asText());
        assertThat(dataPagamento.getDayOfMonth()).isEqualTo(24);
        assertThat(dataPagamento.getMonthValue()).isEqualTo(11);
        assertThat(dataPagamento.getYear()).isEqualTo(2024);
    }

    // Teste de valor limite: Pagamento com valor superior
    @Test
    void registrarPagamento_ValorSuperior_DeveRetornarValorIncorreto() throws Exception {
        String url = "http://localhost:" + port + "/registrarpagamento" +
                "?dia=24&mes=11&ano=2024&codass=1&valorPago=50.00"; // Valor superior ao custo

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode jsonResponse = objectMapper.readTree(response.getBody());

        assertThat(jsonResponse.get("status").asText()).isEqualTo("VALOR_INCORRETO");
        assertThat(jsonResponse.get("valorEstornado").asDouble()).isEqualTo(50.00);
    }

    // Teste de particionamento: Pagamento com valor inferior
    @Test
    void registrarPagamento_ValorInferior_DeveRetornarValorIncorreto() throws Exception {
        String url = "http://localhost:" + port + "/registrarpagamento" +
                "?dia=24&mes=11&ano=2024&codass=1&valorPago=10.00"; // Valor inferior ao custo

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        JsonNode jsonResponse = objectMapper.readTree(response.getBody());

        assertThat(jsonResponse.get("status").asText()).isEqualTo("VALOR_INCORRETO");
        assertThat(jsonResponse.get("valorEstornado").asDouble()).isEqualTo(10.00);
    }

    // Teste de particionamento: Assinatura válida
    @Test
    void verificarAssinaturaValida_AssinaturaAtiva_DeveRetornarTrue() {
        String url = "http://localhost:" + port + "/assinvalida/1";

        ResponseEntity<Boolean> response = testRestTemplate.getForEntity(url, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();
    }

    // Teste de particionamento: Assinatura inválida
    @Test
    void verificarAssinaturaValida_AssinaturaInativa_DeveRetornarFalse() {
        String url = "http://localhost:" + port + "/assinvalida/2";

        ResponseEntity<Boolean> response = testRestTemplate.getForEntity(url, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }

    // Teste de valor limite: Data inválida no pagamento
     @Test
    void registrarPagamento_DataInvalida_DeveRetornarBadRequest() {
        String url = "http://localhost:" + port + "/registrarpagamento" +
                "?dia=31&mes=2&ano=2024&codass=1&valorPago=29.99"; // Data inválida (31 de fevereiro)

        ResponseEntity<String> response = testRestTemplate.postForEntity(url, null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    } 

    // Teste de particionamento: Assinatura inexistente
    @Test
    void verificarAssinaturaValida_AssinaturaInexistente_DeveRetornarFalse() {
        String url = "http://localhost:" + port + "/assinvalida/999"; // Assinatura inexistente

        ResponseEntity<Boolean> response = testRestTemplate.getForEntity(url, Boolean.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isFalse();
    }
}
