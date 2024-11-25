package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA;

import org.springframework.data.repository.ListCrudRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Assinatura;

import java.util.List;

public interface AssinaturaJPA_ItfRep extends ListCrudRepository<Assinatura, Long> {
    List<Assinatura> findByClienteCodigo(Long clienteId);
    List<Assinatura> findByAplicativoCodigo(Long aplicativoId);
}
