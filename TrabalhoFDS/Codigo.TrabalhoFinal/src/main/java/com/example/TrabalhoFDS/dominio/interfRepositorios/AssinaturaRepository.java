package com.example.TrabalhoFDS.dominio.interfRepositorios;


import java.util.List;
import java.util.Optional;

import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;

public interface AssinaturaRepository {
    Optional<AssinaturaModel> findById(Long id);  // Buscar assinatura por ID
    List<AssinaturaModel> findAll();              // Listar todas as assinaturas
    
    // Corrigido para "findByClienteId" conforme o atributo "cliente"
    List<AssinaturaModel> findByClienteCodigo(Long clienteId); // Buscar assinaturas por cliente
    
    // Corrigido para "findByAplicativoId" conforme o atributo "aplicativo"
    List<AssinaturaModel> findByAplicativoCodigo(Long aplicativoId); // Buscar assinaturas por aplicativo
    
    AssinaturaModel save(AssinaturaModel assinatura);  // Criar ou atualizar uma assinatura
    void deleteById(Long id);                // Remover uma assinatura
}
