package com.example.TrabalhoFDS.dominio.servicos;

import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoDeClientes {
    
    private final ClienteRepository clienteRepository;

    public ServicoDeClientes(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Lista todos os clientes
    public List<ClienteModel> listarTodosClientes() {
        return clienteRepository.findAll();
    }
}
