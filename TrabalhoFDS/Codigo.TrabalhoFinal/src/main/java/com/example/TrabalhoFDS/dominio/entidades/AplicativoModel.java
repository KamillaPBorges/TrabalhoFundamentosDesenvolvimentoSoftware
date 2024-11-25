package com.example.TrabalhoFDS.dominio.entidades;

public class AplicativoModel {
    private Long codigo;
    private String nome;
    private float custo;

    
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
