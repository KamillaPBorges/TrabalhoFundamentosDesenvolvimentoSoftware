
package com.example.TrabalhoFDS.aplicacao.dtos;

import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;

public class ClienteDTO {
    private Long codigo;
    private String nome;
    private String email;

    // Construtor vazio
    public ClienteDTO() {}

    // Construtor com parâmetros
    public ClienteDTO(Long codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    // Método estático para converter de ClienteModel para ClienteDTO
    public static ClienteDTO fromModel(ClienteModel cliente) {
        return new ClienteDTO(cliente.getCodigo(), cliente.getNome(), cliente.getEmail());
    }

    // Getters e setters
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
