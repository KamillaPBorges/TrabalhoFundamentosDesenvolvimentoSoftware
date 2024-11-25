package com.example.TrabalhoFDS.testes.testesUnitarios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.TrabalhoFDS.dominio.servicos.ServicoDePagamentos;
import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AssinaturaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class ServicoDePagamentosTest {

    @Autowired
    private ServicoDePagamentos servicoDePagamentos;

    @MockBean
    private AssinaturaRepository assinaturaRepository;

    private AssinaturaModel criarAssinaturaComAplicativoECliente(float custo, Long clienteId) {
        AplicativoModel aplicativo = new AplicativoModel();
        aplicativo.setCodigo(1L);
        aplicativo.setNome("App de Música");
        aplicativo.setCusto(custo);

        ClienteModel cliente = new ClienteModel();
        cliente.setCodigo(clienteId);
        cliente.setNome("João Silva");
        cliente.setEmail("joao.silva@example.com");

        AssinaturaModel assinatura = new AssinaturaModel();
        assinatura.setCodigo(1L);
        assinatura.setAplicativo(aplicativo);
        assinatura.setCliente(cliente);

        return assinatura;
    }

    @Test
    void processarPagamento_Mensal_ComDescontoDe5Porcento() {
        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(29.99f, 1L);
        long dataAtual = System.currentTimeMillis();
        assinatura.setFimVigencia(new Date(dataAtual));

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(29.99f);
        pagamento.setDataPagamento(new Date(dataAtual));

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(1L, pagamento);

        long trintaDiasEmMilissegundos = 30L * 24 * 60 * 60 * 1000;
        Date validadeEsperada = new Date(dataAtual + trintaDiasEmMilissegundos);
        float valorEstornadoEsperado = 29.99f * 0.05f;

        assertThat(resultado.getAssinatura().getFimVigencia()).isEqualTo(validadeEsperada);
        assertThat(resultado.getValorEstornado()).isEqualTo(valorEstornadoEsperado);
    }

    @Test
    void processarPagamento_Anual_ComDescontoDe10Porcento() {
        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(50.0f, 1L);
        long dataAtual = System.currentTimeMillis();
        assinatura.setFimVigencia(new Date(dataAtual));

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(600.0f);
        pagamento.setDataPagamento(new Date(dataAtual));

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(1L, pagamento);

        long umAnoEmMilissegundos = 365L * 24 * 60 * 60 * 1000;
        Date validadeEsperada = new Date(dataAtual + umAnoEmMilissegundos);
        float valorEstornadoEsperado = 600.0f * 0.10f;

        assertThat(resultado.getAssinatura().getFimVigencia()).isEqualTo(validadeEsperada);
        assertThat(resultado.getValorEstornado()).isEqualTo(valorEstornadoEsperado);
    }

    @Test
    void processarPagamento_Reativacao_ComPagamentoMensal() {
        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(29.99f, 1L);
        assinatura.setFimVigencia(new Date(System.currentTimeMillis() - 100000000L));

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(29.99f);
        pagamento.setDataPagamento(new Date());

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(1L, pagamento);

        long quarentaECincoDiasEmMilissegundos = 45L * 24 * 60 * 60 * 1000;
        assertThat(resultado.getAssinatura().getFimVigencia().getTime())
                .isEqualTo(pagamento.getDataPagamento().getTime() + quarentaECincoDiasEmMilissegundos);
        assertThat(resultado.getValorEstornado()).isNull();
    }

    @Test
    void processarPagamento_Reativacao_ComPagamentoAnual() {
        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(50.0f, 1L);
        assinatura.setFimVigencia(new Date(System.currentTimeMillis() - 100000000L));

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(600.0f);
        pagamento.setDataPagamento(new Date());

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(1L, pagamento);

        long umAnoEmMilissegundos = 365L * 24 * 60 * 60 * 1000;
        assertThat(resultado.getAssinatura().getFimVigencia().getTime())
                .isEqualTo(pagamento.getDataPagamento().getTime() + umAnoEmMilissegundos);
        assertThat(resultado.getValorEstornado()).isNull();
    }

    @Test
    void processarPagamento_ValorIncorreto() {
        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(50.0f, 1L);
        long dataAtual = System.currentTimeMillis();
        assinatura.setFimVigencia(new Date(dataAtual));

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(100.0f); // Valor incorreto
        pagamento.setDataPagamento(new Date(dataAtual));

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(1L, pagamento);

        assertThat(resultado.getValorEstornado()).isEqualTo(100.0f);
    }

    // novo teste
    @Test
    void processarPagamento_Reativacao_ComPagamentoAnual_CorrigeValidade() {
        // Criação da assinatura simulada com status de cancelada
        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(100.0f, 1L);
        assinatura.setFimVigencia(new Date(System.currentTimeMillis() - 100000000L)); // Data expirada

        // Mock do repositório para retornar a assinatura simulada
        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        // Criação do pagamento anual
        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(1200.0f); // 12 vezes o custo mensal (anual)
        pagamento.setDataPagamento(new Date()); // Data do pagamento

        // Processamento do pagamento
        PagamentoModel resultado = servicoDePagamentos.processarPagamento(1L, pagamento);

        // Validações
        long umAnoEmMilissegundos = 365L * 24 * 60 * 60 * 1000;
        assertThat(resultado.getAssinatura().getFimVigencia().getTime())
                .isEqualTo(pagamento.getDataPagamento().getTime() + umAnoEmMilissegundos);

        // Em reativação, não deve haver valor estornado
        assertThat(resultado.getValorEstornado()).isNull();
    }






}
