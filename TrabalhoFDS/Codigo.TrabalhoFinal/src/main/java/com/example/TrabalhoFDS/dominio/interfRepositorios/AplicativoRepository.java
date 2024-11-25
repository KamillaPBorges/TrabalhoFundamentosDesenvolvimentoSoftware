package com.example.TrabalhoFDS.dominio.interfRepositorios;

import java.util.List;
import java.util.Optional;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;

public interface AplicativoRepository {
    Optional<AplicativoModel> findById(Long id);  // Buscar por ID
    List<AplicativoModel> findAll();              // Listar todos os aplicativos
    AplicativoModel save(AplicativoModel aplicativo);  // Criar ou atualizar um aplicativo
    void deleteById(Long id);                // Remover um aplicativo
}
