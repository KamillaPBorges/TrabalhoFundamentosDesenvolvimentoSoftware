package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import com.example.TrabalhoFDS.aplicacao.dtos.AplicativoDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAplicativos;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaTodosAplicativosUC {
    private final ServicoDeAplicativos servicoDeAplicativos;

    public ListaTodosAplicativosUC(ServicoDeAplicativos servicoDeAplicativos) {
        this.servicoDeAplicativos = servicoDeAplicativos;
    }

    public List<AplicativoDTO> run() {
        return servicoDeAplicativos.listarTodosAplicativos()
                .stream()
                .map(AplicativoDTO::fromModel)
                .collect(Collectors.toList());
    }
}
