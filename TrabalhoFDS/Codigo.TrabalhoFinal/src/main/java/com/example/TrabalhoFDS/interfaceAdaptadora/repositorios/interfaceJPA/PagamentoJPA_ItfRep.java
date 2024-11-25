package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA;

import org.springframework.data.repository.ListCrudRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Pagamento;

import java.util.List;

public interface PagamentoJPA_ItfRep extends ListCrudRepository<Pagamento, Long> {
    List<Pagamento> findByAssinaturaCodigo(Long assinaturaId);
}
