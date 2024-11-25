package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA;

import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Cliente;
import org.springframework.data.repository.ListCrudRepository;
import java.util.List;

public interface ClienteJPA_ItfRep extends ListCrudRepository<Cliente, Long> {
    List<Cliente> findByAssinaturasAplicativoCodigo(Long aplicativoId);
}
