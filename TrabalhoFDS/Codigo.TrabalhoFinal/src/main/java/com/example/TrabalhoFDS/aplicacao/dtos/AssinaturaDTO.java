package com.example.TrabalhoFDS.aplicacao.dtos;

import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import java.util.Date;

public class AssinaturaDTO {
    private Long codigo;
    private Long aplicativoId;
    private Long clienteId;
    private Date inicioVigencia;
    private Date fimVigencia;
    private String status;

    public AssinaturaDTO(Long codigo, Long aplicativoId, Long clienteId, Date inicioVigencia, Date fimVigencia, String status) {
        this.codigo = codigo;
        this.aplicativoId = aplicativoId;
        this.clienteId = clienteId;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.status = status;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Long getAplicativoId() {
        return aplicativoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public String getStatus() {
        return status;
    }

    // Método estático para converter AssinaturaModel em AssinaturaDTO, com cálculo de status
    public static AssinaturaDTO fromModel(AssinaturaModel assinatura) {
        String status = (assinatura.getFimVigencia().after(new Date())) ? "ATIVA" : "CANCELADA";
        return new AssinaturaDTO(
            assinatura.getCodigo(),
            assinatura.getAplicativo().getCodigo(),
            assinatura.getCliente().getCodigo(),
            assinatura.getInicioVigencia(),
            assinatura.getFimVigencia(),
            status
        );
    }
}
