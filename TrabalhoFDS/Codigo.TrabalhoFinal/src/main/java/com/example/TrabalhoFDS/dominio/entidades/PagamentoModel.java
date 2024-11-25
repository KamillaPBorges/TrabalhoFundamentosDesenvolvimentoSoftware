package com.example.TrabalhoFDS.dominio.entidades;

import java.util.Date;

public class PagamentoModel {
    private Long codigo;
    private AssinaturaModel assinatura;
    private float valorPago;
    private Date dataPagamento;
    private String promocao;
    private Float valorEstornado;


    
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public AssinaturaModel getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(AssinaturaModel assinatura) {
        this.assinatura = assinatura;
    }

    public float getValorPago() {
        return valorPago;
    }

    public void setValorPago(float valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {
        this.promocao = promocao;
    }

    public Float getValorEstornado() { // Getter para valorEstornado
        return valorEstornado;
    }

    public void setValorEstornado(Float valorEstornado) { // Setter para valorEstornado
        this.valorEstornado = valorEstornado;
    }
}

