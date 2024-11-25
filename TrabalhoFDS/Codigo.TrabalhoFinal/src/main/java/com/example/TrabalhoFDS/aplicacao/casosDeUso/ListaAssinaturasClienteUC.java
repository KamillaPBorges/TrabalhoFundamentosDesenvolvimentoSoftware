package com.example.TrabalhoFDS.aplicacao.casosDeUso;
import com.example.TrabalhoFDS.aplicacao.dtos.AssinaturaDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDeAssinaturas;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaAssinaturasClienteUC {
    private final ServicoDeAssinaturas servicoDeAssinaturas;

    public ListaAssinaturasClienteUC(ServicoDeAssinaturas servicoDeAssinaturas) {
        this.servicoDeAssinaturas = servicoDeAssinaturas;
    }

    public List<AssinaturaDTO> run(Long clienteId) {
        // Chama o serviço de assinaturas para listar as assinaturas do cliente
        return servicoDeAssinaturas.listarAssinaturasCliente(clienteId)
                .stream()
                .map(AssinaturaDTO::fromModel)
                .collect(Collectors.toList());
    }
}
