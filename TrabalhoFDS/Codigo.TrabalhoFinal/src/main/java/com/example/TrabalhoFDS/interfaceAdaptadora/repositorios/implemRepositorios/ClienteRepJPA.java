package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.implemRepositorios;
import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.ClienteRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Cliente;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA.ClienteJPA_ItfRep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteRepJPA implements ClienteRepository {
    private final ClienteJPA_ItfRep clienteRepository;

    public ClienteRepJPA(ClienteJPA_ItfRep clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Optional<ClienteModel> findById(Long id) {
        return clienteRepository.findById(id)
                .map(Cliente::toModel);
    }

    @Override
    public List<ClienteModel> findAll() {
        return clienteRepository.findAll().stream()
                .map(Cliente::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteModel> findClientesByAplicativoCodigo(Long aplicativoId) {
        return clienteRepository.findByAssinaturasAplicativoCodigo(aplicativoId).stream()
                .map(Cliente::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteModel save(ClienteModel cliente) {
        Cliente clienteEntity = Cliente.fromModel(cliente);
        Cliente savedEntity = clienteRepository.save(clienteEntity);
        return Cliente.toModel(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
