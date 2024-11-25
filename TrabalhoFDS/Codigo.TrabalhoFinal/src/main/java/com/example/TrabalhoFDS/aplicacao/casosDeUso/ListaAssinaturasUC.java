package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import com.example.TrabalhoFDS.aplicacao.dtos.AssinaturaDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAssinaturas;
import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaAssinaturasUC {
    private final ServicoDeAssinaturas servicoDeAssinaturas;

    public ListaAssinaturasUC(ServicoDeAssinaturas servicoDeAssinaturas) {
        this.servicoDeAssinaturas = servicoDeAssinaturas;
    }

    public List<AssinaturaDTO> run(String tipo) {
        List<AssinaturaModel> assinaturas = servicoDeAssinaturas.listarAssinaturasPorTipo(tipo);
        return assinaturas.stream()
                          .map(AssinaturaDTO::fromModel)
                          .collect(Collectors.toList());
    }
}
