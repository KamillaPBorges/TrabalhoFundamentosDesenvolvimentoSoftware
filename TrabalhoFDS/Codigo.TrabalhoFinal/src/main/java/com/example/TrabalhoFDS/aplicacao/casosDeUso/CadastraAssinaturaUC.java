package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import org.springframework.stereotype.Component;
import com.example.TrabalhoFDS.aplicacao.dtos.CriarAssinaturaDTO;
import com.example.TrabalhoFDS.aplicacao.dtos.AssinaturaDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAssinaturas;
import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;

@Component
public class CadastraAssinaturaUC {
    private final ServicoDeAssinaturas servicoDeAssinaturas;

    public CadastraAssinaturaUC(ServicoDeAssinaturas servicoDeAssinaturas) {
        this.servicoDeAssinaturas = servicoDeAssinaturas;
    }

    public AssinaturaDTO run(CriarAssinaturaDTO criarAssinaturaDTO) {
        AssinaturaModel assinaturaSalva = servicoDeAssinaturas.cadastrarAssinatura(
            criarAssinaturaDTO.getClienteId(),
            criarAssinaturaDTO.getAplicativoId()
        );

        return AssinaturaDTO.fromModel(assinaturaSalva);
    }
}
