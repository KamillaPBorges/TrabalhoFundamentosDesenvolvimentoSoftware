package com.example.TrabalhoFDS.aplicacao.dtos;

import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;
import java.util.Date;
public class PagamentoDTO {
    private Date dataPagamento;
    private String status;  // Campo para indicar o status
    private Float valorEstornado;  // Campo para valor estornado

    public PagamentoDTO() {}

    public PagamentoDTO(Date dataPagamento, String status, Float valorEstornado) {
        this.dataPagamento = dataPagamento;
        this.status = status;
        this.valorEstornado = valorEstornado;
    }

    // Getters e setters

    public Date getdataPagamento() {
        return dataPagamento;
    }

    public void dataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getValorEstornado() {
        return valorEstornado;
    }

    public void setValorEstornado(Float valorEstornado) {
        this.valorEstornado = valorEstornado;
    }

    // Método para converter de PagamentoModel para PagamentoDTO com status e valorEstornado
    public static PagamentoDTO fromModel(PagamentoModel pagamento, String status, Float valorEstornado) {
        return new PagamentoDTO(
            pagamento.getDataPagamento(),
            status,
            valorEstornado
        );
    }
}
