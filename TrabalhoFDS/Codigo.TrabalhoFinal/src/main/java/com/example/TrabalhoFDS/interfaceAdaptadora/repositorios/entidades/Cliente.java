package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades;

import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cliente {
    @Id
    private Long codigo;
    private String nome;
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assinatura> assinaturas;

    protected Cliente() {}

    public Cliente(Long codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public static Cliente fromModel(ClienteModel model) {
        return new Cliente(model.getCodigo(), model.getNome(), model.getEmail());
    }

    public static ClienteModel toModel(Cliente cliente) {
        ClienteModel model = new ClienteModel();
        model.setCodigo(cliente.getCodigo());
        model.setNome(cliente.getNome());
        model.setEmail(cliente.getEmail());
        return model;
    }
}
