package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import com.example.TrabalhoFDS.aplicacao.dtos.AssinaturaDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAssinaturas;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaClientesPorAplicativoUC {
    private final ServicoDeAssinaturas servicoDeAssinaturas;

    public ListaClientesPorAplicativoUC(ServicoDeAssinaturas servicoDeAssinaturas) {
        this.servicoDeAssinaturas = servicoDeAssinaturas;
    }

    public List<AssinaturaDTO> run(Long aplicativoId) {
        // Obtém as assinaturas associadas ao aplicativo e converte para AssinaturaDTO
        return servicoDeAssinaturas.listarAssinaturasPorAplicativo(aplicativoId)
                .stream()
                .map(AssinaturaDTO::fromModel)
                .collect(Collectors.toList());
    }
}
