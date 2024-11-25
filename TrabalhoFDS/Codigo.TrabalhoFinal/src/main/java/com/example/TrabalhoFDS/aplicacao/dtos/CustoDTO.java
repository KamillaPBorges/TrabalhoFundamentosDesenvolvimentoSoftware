package com.example.TrabalhoFDS.aplicacao.dtos;

public class CustoDTO {
    private float custo;

    // Construtor vazio
    public CustoDTO() {}

    // Construtor com parâmetros
    public CustoDTO(float custo) {
        this.custo = custo;
    }

    // Getter e setter
    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }
}
