package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAssinaturas;
import org.springframework.stereotype.Component;

@Component
public class ValidaAssinaturaUC {
    private final ServicoDeAssinaturas servicoDeAssinaturas;

    public ValidaAssinaturaUC(ServicoDeAssinaturas servicoDeAssinaturas) {
        this.servicoDeAssinaturas = servicoDeAssinaturas;
    }

    public boolean run(Long codass) {
        return servicoDeAssinaturas.isAssinaturaValida(codass);
    }
}
