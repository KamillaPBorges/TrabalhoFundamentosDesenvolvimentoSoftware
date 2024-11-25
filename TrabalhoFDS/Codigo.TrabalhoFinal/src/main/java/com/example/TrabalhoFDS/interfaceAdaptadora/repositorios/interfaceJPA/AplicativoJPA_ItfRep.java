package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA;


import org.springframework.data.repository.ListCrudRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Aplicativo;

public interface AplicativoJPA_ItfRep extends ListCrudRepository<Aplicativo, Long> {
}
