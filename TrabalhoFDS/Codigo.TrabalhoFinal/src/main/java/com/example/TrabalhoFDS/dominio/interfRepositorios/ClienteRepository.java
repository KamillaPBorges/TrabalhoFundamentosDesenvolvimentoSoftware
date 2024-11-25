package com.example.TrabalhoFDS.dominio.interfRepositorios;

import java.util.List;
import java.util.Optional;

import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;

public interface ClienteRepository {
    Optional<ClienteModel> findById(Long id);  // Buscar cliente por ID
    List<ClienteModel> findAll();              // Listar todos os clientes
    
    // Corrigido para buscar clientes por aplicativo
    List<ClienteModel> findClientesByAplicativoCodigo(Long aplicativoId);
    
    ClienteModel save(ClienteModel cliente);   // Criar ou atualizar um cliente
    void deleteById(Long id);                  // Remover um cliente
}
