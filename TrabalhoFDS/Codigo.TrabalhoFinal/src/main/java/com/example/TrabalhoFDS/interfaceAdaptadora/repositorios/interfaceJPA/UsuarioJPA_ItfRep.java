package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA;

import org.springframework.data.repository.ListCrudRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Usuario;

public interface UsuarioJPA_ItfRep extends ListCrudRepository<Usuario, String> {
}
