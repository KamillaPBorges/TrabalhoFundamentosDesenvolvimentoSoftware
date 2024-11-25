package com.example.TrabalhoFDS.testes.testerIntegrados;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AssinaturaRepository;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDePagamentos;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ServicoDePagamentosIntegrationTest {

    @Autowired
    private ServicoDePagamentos servicoDePagamentos;

    @MockBean
    private AssinaturaRepository assinaturaRepository;

    private AssinaturaModel criarAssinaturaComAplicativoECliente(float custo, Long clienteId, Long assinaturaId) {
        AplicativoModel aplicativo = new AplicativoModel();
        aplicativo.setCodigo(1L);
        aplicativo.setNome("Aplicativo de Teste");
        aplicativo.setCusto(custo);

        ClienteModel cliente = new ClienteModel();
        cliente.setCodigo(clienteId);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");

        AssinaturaModel assinatura = new AssinaturaModel();
        assinatura.setCodigo(assinaturaId);
        assinatura.setAplicativo(aplicativo);
        assinatura.setCliente(cliente);

        return assinatura;
    }

    @Test
    void processarPagamento_Sucesso() {
        Long codAssinatura = 1L;
        float valorPago = 29.99f;
        Date dataPagamento = new Date();

        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(29.99f, 1L, codAssinatura);
        assinatura.setFimVigencia(new Date(System.currentTimeMillis()));

        Mockito.when(assinaturaRepository.findById(codAssinatura)).thenReturn(Optional.of(assinatura));
        Mockito.when(assinaturaRepository.save(any(AssinaturaModel.class)))
               .thenAnswer(invocation -> invocation.getArgument(0));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(valorPago);
        pagamento.setDataPagamento(dataPagamento);

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(codAssinatura, pagamento);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getValorPago()).isEqualTo(valorPago);

        Mockito.verify(assinaturaRepository, Mockito.times(1)).save(any(AssinaturaModel.class));
    }

    @Test
    void processarPagamento_ValorIncorreto() {
        Long codAssinatura = 1L;
        float valorPago = 10.00f; // Valor incorreto
        Date dataPagamento = new Date();

        AssinaturaModel assinatura = criarAssinaturaComAplicativoECliente(29.99f, 1L, codAssinatura);
        assinatura.setFimVigencia(new Date());

        Mockito.when(assinaturaRepository.findById(codAssinatura)).thenReturn(Optional.of(assinatura));

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setValorPago(valorPago);
        pagamento.setDataPagamento(dataPagamento);

        PagamentoModel resultado = servicoDePagamentos.processarPagamento(codAssinatura, pagamento);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getPromocao()).isNull(); // Sem promoção para valor incorreto
    }

    
}
