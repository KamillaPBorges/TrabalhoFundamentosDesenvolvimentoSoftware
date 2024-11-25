package com.example.TrabalhoFDS.dominio.interfRepositorios;


import java.util.Optional;

import com.example.TrabalhoFDS.dominio.entidades.UsuarioModel;

public interface UsuarioRepository {
    Optional<UsuarioModel> findByUsuario(String usuario);  // Buscar usuário por nome de usuário
    UsuarioModel save(UsuarioModel usuario);                    // Criar ou atualizar um usuário
}
