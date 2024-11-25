package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades;

import com.example.TrabalhoFDS.dominio.entidades.UsuarioModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    private String usuario;
    private String senha;

    protected Usuario() {}

    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public static Usuario fromModel(UsuarioModel model) {
        return new Usuario(model.getUsuario(), model.getSenha());
    }

    public static UsuarioModel toModel(Usuario usuario) {
        UsuarioModel model = new UsuarioModel();
        model.setUsuario(usuario.getUsuario());
        model.setSenha(usuario.getSenha());
        return model;
    }
}
