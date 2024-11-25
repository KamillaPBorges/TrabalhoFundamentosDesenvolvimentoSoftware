package com.example.TrabalhoFDS.aplicacao.casosDeUso;

import com.example.TrabalhoFDS.aplicacao.dtos.ClienteDTO;
import com.example.TrabalhoFDS.dominio.servicos.ServicoDeClientes;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaTodosClientesUC {
    private final ServicoDeClientes servicoDeClientes;

    public ListaTodosClientesUC(ServicoDeClientes servicoDeClientes) {
        this.servicoDeClientes = servicoDeClientes;
    }

    public List<ClienteDTO> run() {
        return servicoDeClientes.listarTodosClientes()
                .stream()
                .map(ClienteDTO::fromModel)
                .collect(Collectors.toList());
    }
}
