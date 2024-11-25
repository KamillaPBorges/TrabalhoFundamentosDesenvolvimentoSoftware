package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.implemRepositorios;
import com.example.TrabalhoFDS.dominio.entidades.UsuarioModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.UsuarioRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Usuario;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA.UsuarioJPA_ItfRep;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepJPA implements UsuarioRepository {
    private final UsuarioJPA_ItfRep usuarioRepository;

    public UsuarioRepJPA(UsuarioJPA_ItfRep usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<UsuarioModel> findByUsuario(String usuario) {
        return usuarioRepository.findById(usuario)
                .map(Usuario::toModel);
    }

    @Override
    public UsuarioModel save(UsuarioModel usuario) {
        Usuario usuarioEntity = Usuario.fromModel(usuario);
        Usuario savedEntity = usuarioRepository.save(usuarioEntity);
        return Usuario.toModel(savedEntity);
    }
}
