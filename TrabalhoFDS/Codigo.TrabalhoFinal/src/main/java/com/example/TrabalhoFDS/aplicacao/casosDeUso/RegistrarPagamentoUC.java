package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;
import com.example.TrabalhoFDS.aplicacao.dtos.PagamentoDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDePagamentos;
import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class RegistrarPagamentoUC {
    private final ServicoDePagamentos servicoDePagamentos;

    public RegistrarPagamentoUC(ServicoDePagamentos servicoDePagamentos) {
        this.servicoDePagamentos = servicoDePagamentos;
    }

      public PagamentoDTO run(int dia, int mes, int ano, Long codass, double valorPago) {
        Date dataPagamento;
        try {
            LocalDate localDate = LocalDate.of(ano, mes, dia);
            dataPagamento = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Data inválida fornecida: " + dia + "/" + mes + "/" + ano, e);
        }

        PagamentoModel novoPagamento = new PagamentoModel();
        novoPagamento.setValorPago((float) valorPago);
        novoPagamento.setDataPagamento(dataPagamento);

        String status;
        Float valorEstornado = null;

        if (servicoDePagamentos.validarValorPago(codass, valorPago)) {
            status = "PAGAMENTO_OK";
            PagamentoModel pagamentoProcessado = servicoDePagamentos.processarPagamento(codass, novoPagamento);
            valorEstornado = pagamentoProcessado.getValorEstornado();
            System.out.println("DEBUG - Valor estornado capturado no UC: " + valorEstornado);
        } else {
            status = "VALOR_INCORRETO";
            valorEstornado = (float) valorPago;
        }

        return PagamentoDTO.fromModel(novoPagamento, status, valorEstornado);
    }
}
