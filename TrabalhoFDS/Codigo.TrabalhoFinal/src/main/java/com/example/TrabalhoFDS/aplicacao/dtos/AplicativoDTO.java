package com.example.TrabalhoFDS.aplicacao.dtos;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;

public class AplicativoDTO {
    private Long codigo;
    private String nome;
    private float custo;

    // Construtor vazio
    public AplicativoDTO() {}

    // Construtor com parâmetros
    public AplicativoDTO(Long codigo, String nome, float custo) {
        this.codigo = codigo;
        this.nome = nome;
        this.custo = custo;
    }

    // Método estático para converter de AplicativoModel para AplicativoDTO
    public static AplicativoDTO fromModel(AplicativoModel aplicativo) {
        return new AplicativoDTO(aplicativo.getCodigo(), aplicativo.getNome(), aplicativo.getCusto());
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

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }
}
