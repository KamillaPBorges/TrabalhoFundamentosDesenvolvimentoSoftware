package com.example.TrabalhoFDS.dominio.entidades;
import java.util.Date;

public class AssinaturaModel {
    private Long codigo;
    private AplicativoModel aplicativo;
    private ClienteModel cliente;
    private Date inicioVigencia;
    private Date fimVigencia;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public AplicativoModel getAplicativo() {
        return aplicativo;
    }

    public void setAplicativo(AplicativoModel aplicativo) {
        this.aplicativo = aplicativo;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }
}
