package com.example.TrabalhoFDS.aplicacao.dtos;

public class CriarAssinaturaDTO {
    private Long clienteId;
    private Long aplicativoId;

    public CriarAssinaturaDTO() {}

    public CriarAssinaturaDTO(Long clienteId, Long aplicativoId) {
        this.clienteId = clienteId;
        this.aplicativoId = aplicativoId;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getAplicativoId() {
        return aplicativoId;
    }

    public void setAplicativoId(Long aplicativoId) {
        this.aplicativoId = aplicativoId;
    }
}
