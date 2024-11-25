
package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aplicativo {
    @Id
    private Long codigo;
    private String nome;
    private double custoMensal;

    protected Aplicativo() {}

    public Aplicativo(Long codigo, String nome, double custoMensal) {
        this.codigo = codigo;
        this.nome = nome;
        this.custoMensal = custoMensal;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getCustoMensal() {
        return custoMensal;
    }

    public static Aplicativo fromModel(AplicativoModel model) {
        if (model == null) {
            throw new IllegalArgumentException("AplicativoModel não pode ser nulo");
        }
        return new Aplicativo(model.getCodigo(), model.getNome(), model.getCusto());
    }
    

    public static AplicativoModel toModel(Aplicativo app) {
        AplicativoModel model = new AplicativoModel();
        model.setCodigo(app.getCodigo());
        model.setNome(app.getNome());
        model.setCusto((float) app.getCustoMensal());  // Conversão de double para float
        return model;
    }
}
