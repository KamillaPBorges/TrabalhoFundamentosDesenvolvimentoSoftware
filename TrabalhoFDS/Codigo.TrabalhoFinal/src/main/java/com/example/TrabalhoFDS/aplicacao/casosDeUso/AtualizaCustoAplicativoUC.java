package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import com.example.TrabalhoFDS.aplicacao.dtos.AplicativoDTO;

import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAplicativos;
import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import org.springframework.stereotype.Component;

@Component
public class AtualizaCustoAplicativoUC {
    private final ServicoDeAplicativos servicoDeAplicativos;

    public AtualizaCustoAplicativoUC(ServicoDeAplicativos servicoDeAplicativos) {
        this.servicoDeAplicativos = servicoDeAplicativos;
    }

    public AplicativoDTO run(Long idAplicativo, float custo) {
        AplicativoModel aplicativo = servicoDeAplicativos.buscarAplicativo(idAplicativo);
        aplicativo.setCusto(custo);
        AplicativoModel atualizado = servicoDeAplicativos.atualizarAplicativo(aplicativo);
        return AplicativoDTO.fromModel(atualizado);
    }
    
    
}
