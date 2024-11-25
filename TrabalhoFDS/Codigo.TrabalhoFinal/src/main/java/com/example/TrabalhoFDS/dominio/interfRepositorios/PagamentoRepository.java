package com.example.TrabalhoFDS.dominio.interfRepositorios;

import java.util.List;
import java.util.Optional;

import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;

public interface PagamentoRepository {
    Optional<PagamentoModel> findById(Long id);  // Buscar pagamento por ID
    List<PagamentoModel> findAll();              // Listar todos os pagamentos
    PagamentoModel save(PagamentoModel pagamento);    // Criar ou atualizar um pagamento
    List<PagamentoModel> findByAssinaturaCodigo(Long assinaturaId);
}
